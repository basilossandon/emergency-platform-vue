<template>
  <div>
    <h1>
      <b>Search for Volunteers</b>
    </h1>
    <div class="container">
      <div class="row">
        <div class="filters">

        </div>
      </div>

      <div class="row">
        <l-map
          style="margin-left: 60px; margin-top: 0% ;height: 450px; width: 90%"
          :zoom="zoom"
          :center="center"
        >
          <l-tile-layer :url="url"></l-tile-layer>
          <l-marker
            :icon="icon"
            v-for="volunteer in volunteers"
            :key="volunteer.id"
            :lat-lng="[volunteer.latitude, volunteer.longitude]"
          ></l-marker>
        </l-map>
      </div>
    </div>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";
import axios from "axios";

export default {
  components: { LMap, LTileLayer, LMarker },
  data() {
    return {
      volunteers: [],
      volunteerID: "",
      url:
        "https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=t3SMZ47V58YWCHSToO2d",

      zoom: 6,
      center: [-33.4489, -70.6693],
      // eslint-disable-next-line
      icon: L.icon({
        iconUrl: "https://i.imgur.com/fmK9aII.png",
        iconSize: [32, 37],
        iconAnchor: [16, 37]
      })
    };
  },
  methods: {
    
  },
  mounted: function() {
        console.log("WEna loco");
      axios.get(`http://localhost:4567/volunteers`).then(response => {
        console.log(response.data);
        this.volunteers = response.data;
      });
    }
};

</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 45% 55%;
  grid-template-rows: 2fr;
}

.map {
  height: 600px;
  width: 100%;
}
</style>
