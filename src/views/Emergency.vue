<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="emergencyData">
          <div class="scroll scroll4" style="overflow: auto; height:600px;">
            <el-card
              :body-style="{ padding: '0px' }"
              v-for="emergency in emergencies"
              :key="emergency.id"
            >
              <div class="cardBody">
                <img
                  :src="
                    'https://loremflickr.com/160/120/earthquake,tsunami,flooding?lock=' +
                      emergency.id
                  "
                />

                <div class="info-container">
                  <div style="padding: 14px;">
                    <h3>{{ emergency.name }}</h3>
                  </div>

                  <div class="emergencyButtons">
                    <el-button
                      style="position:absolute; bottom:0; right:0; margin-bottom:5px; margin-right:5px;"
                      type="danger"
                      icon="el-icon-delete"
                      circle
                      v-on:click="deleteEmergency(emergency.id)"
                    ></el-button>


                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
      <div class="row">
        <l-map style="margin-left: 60px; margin-top: 0%;height: 100%; width: 90%" :zoom="zoom" :center="center">
          <l-tile-layer :url="url"></l-tile-layer>
          <l-circle-marker :lat-lng="circle.center" :radius="circle.radius" :color="circle.color" />
          <l-marker
            :icon="icon"
            v-for="emergency in emergencies"
            :key="emergency.id"
            :lat-lng="[emergency.latitude, emergency.longitude]"
            @click="loadEmergency(emergency)"
          >
            <l-popup>
              {{ emergency.name }}
            </l-popup>
          </l-marker>
        </l-map>
      </div>
      

    </div>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LPopup, LCircleMarker } from "vue2-leaflet";
import axios from "axios";

export default {
  components: { LMap, LTileLayer, LMarker, LPopup, LCircleMarker },
  data() {
    return {
      emergencies: [],
      emergencyID: "",
      loadedEmergency: {
        name: "Select an emergency",
        latitude: -33.4489,
        longitude: -70.6693
      },
      tempEmergency: { name: "Select an emergency" },
      strength: null,
      dextery: null,
      knowledge: null,
      motivation: null,
      leadership: null,
      circle: {
        center: [-33.4489, -70.6693],
        radius: 0,
        color: "red"
      },
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
    loadEmergency(emergency) {
      this.loadedEmergency = emergency;
      this.circle.center = [emergency.latitude, emergency.longitude];
    },
    getVolunteersAround(emergency) {
      console.log(emergency.name);
    },
    deleteEmergency(emergencyID) {
      this.$confirm(
        "Are you sure you want to remove this emergency?",
        "Remove emergency",
        {
          confirmButtonText: "Yes",
          cancelButtonText: "No",
          type: "warning"
        }
      ).then(
        function() {
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
          this.$message("You have succesfully deleted the emergency!");
        }.bind(this)
      );
    },
    updateEmergency(
      emergencyID,
      emergencyName,
      emergencyLocation,
      emergencyStatus
    ) {
      axios({
        method: "put",
        url: "http://localhost:4567/emergencies/" + emergencyID,
        emergencyID: emergencyID,
        data: {
          name: emergencyName,
          location: emergencyLocation,
          status: emergencyStatus
        },
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/emergencies`)
            .then(response => {
              this.emergencies = response.data;
              this.$notify({
                title: "Emergency updated",
                message: "Emergency updated succesfully.",
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
    completeEmergency(emergencyID, emergencyName, emergencyLocation) {
      axios({
        method: "put",
        url: "http://localhost:4567/emergencies/" + emergencyID,
        emergencyID: emergencyID,
        data: {
          name: emergencyName,
          location: emergencyLocation,
          status: "Complete"
        },
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/emergencies`)
            .then(response => {
              this.emergencies = response.data;
              this.$notify({
                title: "Emergency completed",
                message: "Emergency completed succesfully.",
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
.container {
  display: grid;
  grid-template-columns: 45% 55%;
  grid-template-rows: 2fr;
}

.map {
  height: 600px;
  width: 100%;
}

.el-card {
  border: 0;
  border-radius: 8px;
  height: 118px;
  margin-right: 8px !important;
  box-shadow: 0 1px 2px rgba(60, 64, 67, 0.3),
    0 1px 3px 1px rgba(60, 64, 67, 0.15);
  margin: 2px 2px 8px;
}
.el-card:hover {
  box-shadow: 0 1px 2px rgba(60, 64, 67, 0.6),
    0 1px 3px 1px rgba(60, 64, 67, 0.35);
}

.cardBody {
  display: flex;
}
.info-container {
  width: 333px;
  display: flex;
  position: relative;
}

.scroll4::-webkit-scrollbar {
  width: 10px;
}

.scroll4::-webkit-scrollbar-thumb {
  background: #666;
  border-radius: 20px;
}

.scroll4::-webkit-scrollbar-track {
  background: #ddd;
  border-radius: 20px;
}
</style>
