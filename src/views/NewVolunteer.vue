<template>
  <div class="new-volunteer">
    <h1>New volunteer</h1>
    <form>
      <div class="form-item">
        <label class="element-borders" for="name">Volunteer first name</label>
        <el-input
          class="element-borders"
          id="name"
          placeholder="e.g. John"
          v-model="volunteer.name"
          prefix-icon="el-icon-user-solid"
        ></el-input>
        <label class="element-borders" for="name">Last name</label>
        <el-input
          class="element-borders"
          id="lastname"
          placeholder="e.g. Smith McDonalds"
          v-model="volunteer.lastname"
        ></el-input>
        <label class="element-borders" for="name">RUT</label>
        <el-input
          class="element-borders"
          id="rut"
          placeholder="e.g. 19.345.567-6"
          v-model="volunteer.rut"
          prefix-icon="el-icon-s-check"
        ></el-input>
        <label class="element-borders" for="name">Email</label>
        <el-input
          class="element-borders"
          id="email"
          placeholder="e.g. john.s.mcdonald@gmail.com"
          v-model="volunteer.email"
          prefix-icon="el-icon-message"
        ></el-input>
        <label for="sex"> Gender</label>
        <el-radio-group style="margin-top:15px;" v-model="volunteer.sex">
          <el-radio v-model="volunteer.sex" label="Male">Male</el-radio>
          <el-radio v-model="volunteer.sex" label="Female">Female</el-radio>
          <el-radio v-model="volunteer.sex" label="Other">Other</el-radio>
        </el-radio-group>
    <el-divider content-position="left">Strength </el-divider>
            <el-rate v-model="volunteer.strength" style="margin:2px;" :max=10 show-score   text-color="#ff9900" score-template="{value} points"></el-rate>
    <el-divider content-position="left">Dextery</el-divider>
        <el-rate v-model="volunteer.dextery" style="margin:2px;" :max=10 show-score   text-color="#ff9900" score-template="{value} points"></el-rate>
    <el-divider content-position="left">Knowledge</el-divider>
        <el-rate v-model="volunteer.knowledge" style="margin:2px;" :max=10 show-score   text-color="#ff9900" score-template="{value} points"></el-rate>
    <el-divider content-position="left">Motivation</el-divider>
        <el-rate v-model="volunteer.motivation" style="margin:2px;" :max=10 show-score   text-color="#ff9900" score-template="{value} points"></el-rate>
    <el-divider content-position="left">Leadership</el-divider>
        <el-rate  v-model="volunteer.leadership" style="margin:2px;" :max=10 show-score   text-color="#ff9900" score-template="{value} points"></el-rate>


        <div class="map-container">
          <l-map style="height: 350px; width: 100%; margin-top:20px " :zoom="zoom" :center="center">
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

      <div class="button-volunteer-wrapper">
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
      volunteer: {},
      message: "",
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      maxRate: 10,
      zoom: 8,
      center: [-33.4489, -70.6693],
      marker: {
        position: { lat: -33.4489, lng: -70.6693 }
      }
    };
  },
  methods: {
    setPos: function() {
      this.volunteer.latitude = this.marker.position.lat;
      this.volunteer.longitude = this.marker.position.lng;
    },
    save: async function() {
      this.message = "";
      if (this.volunteer.name == "") {
        this.message = "You must enter a volunteer name";
        this.$notify({
          title: "Warning",
          message: "Volunteer name missing!",
          type: "warning"
        });
        return false;
      }
      if (this.volunteer.capacity == "") {
        this.message = "You must enter the volunteer capacity";
        this.$notify({
          title: "Warning",
          message: "You must enter the volunteer capacity",
          type: "warning"
        });
        return false;
      }
      if (this.volunteer.status == "") {
        this.volunteer.status = "Not assigned";
        console.log(this.volunteer.status);
      }
      try {
        console.log(this.volunteer);
        let response = await this.$http.post("/volunteers", this.volunteer);
        this.message = "Volunteer saved successfully";
        console.log(response);
        this.$message({
          message: "Volunteer succesfully created.",
          type: "success"
        });
      } catch (e) {
        console.log("error", e);
        this.message = "An error has ocurred";
        this.$notify({
          title: "Warning",
          message: "Please include name, age and gender.",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style scoped>
.new-volunteer {
  box-sizing: border-box;
  padding: 8px 0 15px;
  position: relative;
  margin-bottom: 25px;
}
.button-volunteer-wrapper {
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
