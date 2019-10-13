
<template>
  <div class="new-emergency">
    <h1>New emergency</h1>
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
          prefix-icon="el-icon-location"
          v-model="emergency.location"
        ></el-input>
        <label class="element-borders" for="location">Description</label>
        <el-input
          type="textarea"
          class="element-borders"
          id="location"
          placeholder="e.g A tsunami has striked Thailand alongside a typhoon leaving many people stranded and homeless."
          v-model="emergency.description"
        ></el-input>

        <div class="map-container">
          <l-map style="height: 350px; width: 100%" :zoom="zoom" :center="center">
            <l-tile-layer :url="url"></l-tile-layer>
            <l-marker :lat-lng.sync="marker.position" :draggable="true" @move="setPos"></l-marker>
          </l-map>
        </div>

        <el-input
          style="margin-top: 5px"
          id="Latitude"
          placeholder="Latitude"
          name="marker.lat"
          v-model="marker.position.lat"
        />
        <el-input
          style="margin-top: 5px"
          id="Longitude"
          placeholder="Longitude"
          name="marker.lat"
          v-model="marker.position.lng"
        />
      </div>
      <div class="button-emergency-wrapper">
        <el-button type="primary" round icon="el-icon-upload" @click="save">Save</el-button>
      </div>
    </form>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";

export default {
  components: { LMap, LTileLayer, LMarker },

  data: function() {
    return {
      value: "Active",
      radio: "",
      emergency: {
      },
      message: "",
      textarea: "",
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      zoom: 8,
      center: [-33.4489, -70.6693],
      marker: {
        position: { lat: -33.4489, lng: -70.6693 }
      }
    };
  },

  methods: {
    setPos: function(){
      this.emergency.latitude = this.marker.position.lat;
      this.emergency.longitude = this.marker.position.lng;
      this.emergency.status = "Active";
    },

    save: async function() {
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
        console.log(this.emergency);
        let response = await this.$http.post("/emergencies", this.emergency);
        this.message = "Emergency saved successfully";
        console.log(response);
        this.$message({
          message: "Emergency succesfully created.",
          type: "success"
        });
      } catch (e) {
        console.log("error", e);
        this.message = "An error has occurred";
        this.$notify({
          title: "Warning",
          message: "Please include all fields and update the location.",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style scoped>
.new-emergency {
  box-sizing: border-box;
  padding: 8px 0 15px;
  position: relative;
}
.button-emergency-wrapper {
  align-items: center;
  bottom: calc(-100px / 2);
  display: flex;
  flex-direction: column;
  left: 0;
  margin: 0 auto;
  position: absolute;
  right: 0;
}
.element-borders {
  margin-top: 3px;
  margin-bottom: 3px;
}

.map {
  height: 600px;
  width: 100%;
}
</style>
