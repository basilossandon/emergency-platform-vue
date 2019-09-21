<template>
  <div>
    <h1>
      <b>All emergencies</b>
    </h1>
    <div
      role="tablist"
      v-for="emergency in emergencies"
      :key="emergency.id"
      v-bind:title="{ 'emergencyName': emergency.active }"
    >
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item v-bind:title="emergency.name" name="emergency.id">
          <el-row>
            <el-col :span="4">
              <div class="grid-content-image">
                <img
                  class="grid-content"
                  style="border-radius:5px;   box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);"
                  :src="'https://loremflickr.com/160/120/earthquake?lock='+emergency.id"
                />
              </div>
            </el-col>
            <el-col :span="8">
              <div class="grid-content-text">
                Location:{{emergency.location}}
                <br />
                Status: {{emergency.status}}
              </div>
            </el-col>
            <el-col :span="4">
              <div class="grid-content bottom">
                <el-button type="success" icon="el-icon-check" circle></el-button>
                <el-button type="primary" icon="el-icon-edit" circle></el-button>
                <el-button type="danger" icon="el-icon-delete" circle></el-button>
              </div>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div v-if="emergencies.length==0" class="empty-list">
      <em>No emergencies found</em>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emergencies: [],
      /*page:1,
      perPage: 10,*/
      totalRecords: 0,
      pageOptions: {
        texts: {
          count: ""
        }
      }
    };
  },
  methods: {
    paginate: function() {
      //this.page = val
      this.getData();
    },

    getData: async function() {
      try {
        /*let offset = this.perPage*(this.page-1);
        let perPage = this.perPage;
        let resource = this.resource;*/
        let response = await this.$http.get(`/emergencies`);
        this.emergencies = response.data;
        console.log("headers", response.headers);
        this.totalRecords = +response.headers["pagination-count"];
      } catch (e) {
        console.log("error", e);
      }
    }
  },
  created: function() {
    this.getData();
  }
};
</script>

<style scoped>
.bottom {
  position: absolute;
  bottom: 0;
  right: 0;
}

.grid-content-text {
  display: inline-block;
  width: 46%;
  text-align: justify;
  text-justify: inter-word;
  margin-left: 15px;
}

.grid-content-image {
  vertical-align: top;
  width: 46%;
}
.grid-content-crud {
  width: 15%;
}
</style>
