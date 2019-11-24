<template>
  <div>
    <div style="margin-top:0px;" class="home-logo">
 
    </div>
    <div class="home-call">
      <br />
      <h1 class="home-name"></h1>


      <div class="home-container" style="display:flex;">
      <div style="width: 40%; float:left;">
        <img src="../assets/images/3d.png" style=";height: 800x;width:420px;  margin-left: -30px; margin-top: -60px; border-radius: 20px" />
      </div>

      <div style="height: 250px; width: 60%; float: right; display: flex; flex-direction: column; justify-content: center; align-items: center;">
             <i class="md-svg-loader logo-vue-material blending">
        <img src="../assets/images/logo.png" style=";height: 800x;width:320px;" />
      </i>
        <h1>Be a volunteer and change the world.</h1>
        <h3>See live and ongoing emergencies with volunteers below!</h3>
      </div>
      </div>
      

      <l-map style="margin-top: 25px;height: 350px; width: 100%:" :zoom="zoom" :center="center">
        <l-tile-layer :url="url"></l-tile-layer>
        <l-marker
          v-for="emergency in emergencies"
          :key="emergency.id"
          :lat-lng="[emergency.latitude, emergency.longitude]"
          :icon="icon"
        ></l-marker>

        <l-marker
          v-for="volunteer in volunteers"
          :key="volunteer.id"
          :lat-lng="[volunteer.latitude, volunteer.longitude]"
        ></l-marker>
      </l-map>

      <div class="home-actions">
        <a :route="{ name: 'register' }">
          <router-link to="/register">
            <el-button style="margin-top:10px;" type="primary" round>Begin</el-button>
          </router-link>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";

export default {
  components: { LMap, LTileLayer, LMarker },
  data() {
    return {
      url:
        "https://cartodb-basemaps-{s}.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png",
      zoom: 8,
      center: [-33.4489, -70.6693],
      emergencies: [],
      volunteers: [],
      icon: {
        iconUrl: "https://i.imgur.com/fmK9aII.png",
        iconSize: [32, 37],
        iconAnchor: [16, 37]
      }
    };
  },
  methods: {
    created: function() {
      axios.get(`http://localhost:4567/emergencies`).then(response => {
        this.emergencies = response.data;
        console.log(this.emergencies);
      });
      axios.get(`http://localhost:4567/volunteers`).then(response => {
        this.volunteers = response.data;
      });
    }
  },
  beforeMount() {
    this.created();
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