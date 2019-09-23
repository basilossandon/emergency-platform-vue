<template>
  <div>
    <div style="margin-top:50px;" class="home-logo">
      <i class="md-svg-loader logo-vue-material blending">
        <img src="../assets/images/logo.png" style=";height: 800x;width:320px;" />
      </i>
    </div>
    <div class="home-call">
      <br />
      <h1 class="home-name"></h1>
      <p>Be a volunteer and change the world.</p>
      <div class="home-actions">
        <a
          :route="{ name: 'emergency' }"
        >
          <router-link to="/emergencies">
            <el-button type="primary" round>Begin</el-button>
          </router-link>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      value: "Active",
      visible: false,
      activeName: "1",
      emergencies: [],
      emergencyID: ""
    };
  },
  methods: {},
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