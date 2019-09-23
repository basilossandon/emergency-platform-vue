<template>
  <div>
    <h1>
      <b>All emergencies</b>
    </h1>
    <el-collapse
      v-for="emergency in emergencies"
      :key="emergency.id"
      :value="emergency.id"
      v-model="activeName"
      accordion
    >
      <el-collapse-item v-bind:title="emergency.name">
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
              
          <el-popover trigger="click" ref="popover" placement="top" width="160">
            <el-button type="primary"
                icon="el-icon-edit"
                circle  slot="reference"></el-button>
                <form>
      <div class="form-item">
        <label class="element-borders" for="name">Emergency name</label>
        <el-input
          class="element-borders"
          id="name"
          placeholder="e.g. Thailand Tsunami"
          v-model="emergency.name"
        ></el-input>
        <label class="element-borders" for="location">Location</label>
        <el-input
          class="element-borders"
          id="location"
          placeholder="e.g Thailand"
          v-model="emergency.location"
        ></el-input>
        <label for="status">Status</label>
        <el-radio-group style="margin-top:15px;" v-model="emergency.status">
          <el-switch v-model="emergency.status" active-value="Active"
    inactive-value="Inactive">
</el-switch>
        </el-radio-group>
      </div>
      <div class="button-emergency-wrapper">
      </div>
    </form>
            <div style="text-align: right; margin: 0">
              <el-button type="primary"
                icon="el-icon-upload"
                circle @click="update"></el-button>
            </div>
          </el-popover>
              
              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                v-on:click="deleteEmergency(emergency.id)"
              ></el-button>
            </div>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      value: "Active",
      visible:false,
      activeName: "1",
      emergencies: [],
      emergencyID: "",
    };
  },
  methods: {
    deleteEmergency(emergencyID) {
      axios({
        method: "delete",
        url: "http://localhost:4567/emergencies/" + emergencyID,
        emergencyID: emergencyID,
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/emergencies`)
            .then(response => {
              this.emergencies = response.data;
              this.$notify({
                title: "Emergency removed",
                message: "Emergency removed succesfully.",
                type: "success"
              });
            });
        })
        .catch(error => {
          this.$notify.error({
            title: "Error",
            message:
              "Sorry, we can't process your request now: " + error.message
          });
        });
    },
    update: async function() {
      this.message = "";
      if (this.emergency.name == "") {
        this.message = "You must enter an emergency name";
        this.$notify({
          title: "Warning",
          message: "Emergency name missing!",
          type: "warning"
        });
        return false;
      }
      if (this.emergency.location == "") {
        this.message = "You must enter the capacity of the emergency";
        this.$notify({
          title: "Warning",
          message: "Emergency location missing!",
          type: "warning"
        });

        return false;
      }
      if (this.emergency.status == "") {
        this.$notify({
          title: "Warning",
          message: "Emergency status not selected!",
          type: "warning"
        });
        return false;
      }
      try {
        let response = await this.$http.update("/emergencies", this.emergency);
        this.message = "Emergency updated successfully";
        console.log(response);
        this.$message({
          message: "Emergency succesfully updated.",
          type: "success"
        });
      } catch (e) {
        console.log("error", e);
        this.message = "An error has occurred";
        this.$notify({
          title: "Warning",
          message: "Please include name, location and status.",
          type: "warning"
        });
      }
    }
  },
  created: function() {
    axios.get(`http://localhost:4567/emergencies`).then(response => {
      this.emergencies = response.data;
    });
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