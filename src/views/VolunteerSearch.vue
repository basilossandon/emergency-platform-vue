<template>
  <div>
    <h1>
      <b>Search for Volunteers</b>
    </h1>
    <div class="container" style="margin-top: 3%">
      <div class="row">
        <div class="filters">
          <div class="block" style="text-align: left; margin: 0">
            <h2>How do you want to search?</h2>

            <el-popover placement="down" width="400">
              <p>Select an emergency</p>
              <div style="text-align: left; margin: 0">
                <el-select
                  class="select-emergency"
                  v-model="selectedEmergencyId"
                  placeholder="e.g Thailand Tsunami"
                >
                  <el-option
                    v-for="emergency in emergencies"
                    :key="emergency.id"
                    :label="emergency.name"
                    :value="emergency.id"
                  ></el-option>
                </el-select>
                <p>Input Radius</p>
                <el-slider v-model="circle.radius" max="200"></el-slider>
                <div>
                  <el-button
                    type="primary"
                    style="margin-top:15px"
                    @click="searchByRadius(selectedEmergencyId)"
                  >Search</el-button>
                </div>
              </div>

              <el-button slot="reference">By distance from emergency</el-button>
            </el-popover>

            <div style="margin-top: 20px">
              <el-popover placement="down" width="400">
                <p>Select the attributes you are looking for</p>
                <div style="text-align: left; margin: 0">
                  <el-divider content-position="left">Strength</el-divider>
                  <el-rate
                    v-model="selectedStrength"
                    style="margin:2px;"
                    :max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                  ></el-rate>
                  <el-divider content-position="left">Dextery</el-divider>
                  <el-rate
                    v-model="selectedDextery"
                    style="margin:2px;"
                    :max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                  ></el-rate>
                  <el-divider content-position="left">Knowledge</el-divider>
                  <el-rate
                    v-model="selectedKnowledge"
                    style="margin:2px;"
                    :max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                  ></el-rate>
                  <el-divider content-position="left">Motivation</el-divider>
                  <el-rate
                    v-model="selectedMotivation"
                    style="margin:2px;"
                    :max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                  ></el-rate>
                  <el-divider content-position="left">Leadership</el-divider>
                  <el-rate
                    v-model="selectedLeadership"
                    style="margin:2px;"
                    :max="10"
                    show-score
                    text-color="#ff9900"
                    score-template="{value} points"
                  ></el-rate>

                  <el-button
                    type="primary"
                    style="margin-top:15px"
                    @click="loadVolunteersAttributes(selectedStrength, selectedDextery, selectedKnowledge, selectedMotivation, selectedLeadership)"
                  >Search</el-button>
                </div>

                <el-button slot="reference">By attributes</el-button>
              </el-popover>

              <div style="margin-top: 5%">
                <el-button slot="reference" @click="showVolunteers()">Show all volunteers</el-button>
              </div>

              <div style="margin-top: 5%">
                <el-button slot="reference" @click="clear()">Clear</el-button>
              </div>
            </div>
          </div>
          <div style="width: 60%; margin-top: 10%; margin-left: 15%; float:left;">
            <img src="../assets/svg/search.svg" style=";height: 300px;width:100%;" />
          </div>
        </div>
      </div>

      <div class="row">
        <l-map
          style="margin-left: 60px; margin-top: 0% ;height: 500px; width: 90%"
          :zoom="zoom"
          :center="center"
        >
          <l-tile-layer :url="url"></l-tile-layer>

          <l-marker
            v-for="volunteer in volunteersFound"
            :key="volunteer.id"
            :lat-lng="[volunteer.latitude, volunteer.longitude]"
          >
            <l-popup>
              <b>Name:</b>
              {{ volunteer.name }} {{volunteer.lastname}}
              <b>Sex:</b>
              {{volunteer.sex}}
              <el-button type="primary" style="margin-top:15px">Assign Emergency</el-button>
            </l-popup>
          </l-marker>

          <l-marker
            :icon="icon"
            :key="selectedEmergency.id"
            :label="selectedEmergency.name"
            :lat-lng="[selectedEmergency.latitude, selectedEmergency.longitude]"
            @click="loadEmergency(selectedEmergency)"
          >
            <l-popup>
              <b>Emergency name:</b>
              {{selectedEmergency.name}}
            </l-popup>
          </l-marker>
          <l-circle-marker :lat-lng="circle.center" :radius="circle.radius" :color="circle.color" />
        </l-map>
      </div>
    </div>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LCircleMarker, LPopup } from "vue2-leaflet";
import axios from "axios";

export default {
  components: { LMap, LTileLayer, LMarker, LCircleMarker, LPopup },
  data() {
    return {
      count: 0,
      selectedStrength: 0,
      selectedDextery: 0,
      selectedMotivation: 0,
      selectedLeadership: 0,
      selectedKnowledge: 0,
      selectedEmergency: "",
      selectedEmergencyId: "",
      volunteers: [],
      volunteersFound: [],
      emergencies: [],
      volunteerID: "",
      url:
        "https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=t3SMZ47V58YWCHSToO2d",

      zoom: 6,
      circle: {
        center: [-33.4489, -70.6693],
        radius: 0,
        color: "red"
      },
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
    showVolunteers() {
      if (this.volunteers.length == 0) {
        this.$notify({
          title: "There are no volunteers registered",
          message: "Try creating at least one.",
          type: "error"
        });
      } else {
        this.volunteersFound = this.volunteers;
        this.$notify({
          title: "Success",
          message: "Showing all volunteers",
          type: "success"
        });
      }
    },
    clear() {
      this.volunteersFound = [];
      (this.selectedEmergency = ""),
        (this.selectedEmergencyId = ""),
        (this.selectedStrength = 0);
      this.selectedDextery = 0;
      this.selectedKnowledge = 0;
      this.selectedMotivation = 0;
      this.selectedLeadership = 0;
      this.circle.radius = 0;
      this.$notify({
        title: "Cleared",
        message: "You have cleared the filters",
        type: "success"
      });
    },
    searchByRadius(id) {
      for (var i in this.emergencies) {
        if (this.emergencies[i].id == id) {
          this.selectedEmergency = this.emergencies[i];
        }
      }
      this.circle.center = [
        this.selectedEmergency.latitude,
        this.selectedEmergency.longitude
      ];
      if (this.selectedEmergency != "") {
        this.$notify({
          title: "Filter applied",
          type: "success"
        });
      } else {
        this.$notify({
          title: "Error",
          message: "Please fill all the fields.",
          type: "error"
        });
      }
    },
    loadVolunteersAttributes(
      strength,
      dextery,
      knowledge,
      motivation,
      leadership
    ) {
      if (
        strength == 0 ||
        dextery == 0 ||
        knowledge == 0 ||
        motivation == 0 ||
        leadership == 0
      ) {
        this.$notify({
          title: "Search failed",
          message: "Select all attributes",
          type: "error"
        });
      } else {
        this.volunteersFound = [];
        for (var i in this.volunteers) {
          if (
            this.volunteers[i].strength >= strength &&
            this.volunteers[i].dextery >= dextery &&
            this.volunteers[i].knowledge >= knowledge &&
            this.volunteers[i].motivation >= motivation &&
            this.volunteers[i].leadership >= leadership
          ) {
            this.volunteersFound.push(this.volunteers[i]);
          }
        }
        if (this.volunteersFound.length > 0) {
          this.$notify({
            title: "Search complete",
            message: "Search completed successfully.",
            type: "success"
          });
        } else {
          this.$notify({
            title: "Search complete",
            message: "No results.",
            type: "warning"
          });
        }
      }
    },
    loadRadius(radius) {
      this.circle.radius = radius;
    },
    submitFilters(emergency) {
      this.selectedEmergency = emergency;
      this.circle.center = [
        this.selectedEmergency.latitude,
        this.selectedEmergency.longitude
      ];
    },
    calculateDistance(
      circleLatitude,
      circleLongitude,
      volunteerLatitude,
      volunteerLongitude
    ) {
      this.distance =
        ((volunteerLatitude - circleLatitude) ** 2 +
          (volunteerLongitude - circleLongitude) ** 2) **
        (1 / 2);
    }
  },
  mounted: function() {
    axios.get(`http://localhost:4567/volunteers`).then(response => {
      console.log(response.data);
      this.volunteers = response.data;
    });
    axios.get(`http://localhost:4567/emergencies`).then(response => {
      console.log(response.data);
      this.emergencies = response.data;
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
