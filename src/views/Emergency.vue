<template>
  <div>
    <h1>
      <b>All emergencies</b>
    </h1>
    <l-map style="height: 350px; width: 100%" :zoom="zoom" :center="center">
      <l-tile-layer :url="url"></l-tile-layer>
      <l-marker
        :icon="icon"
        v-for="emergency in emergencies"
        :key="emergency.id"
        :lat-lng="[emergency.latitude, emergency.longitude]"
      ></l-marker>
    </l-map>
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
              <el-button
                type="success"
                icon="el-icon-check"
                v-on:click="completeEmergency(emergency.id, emergency.name, emergency.location)"
                circle
                style="margin-right:3px;"
              ></el-button>
              <el-popover trigger="click" ref="popover" placement="top" width="160">
                <el-button type="primary" icon="el-icon-edit" circle slot="reference"></el-button>
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
                    <el-radio v-model="emergency.status" label="Active">Active</el-radio>
                    <el-radio v-model="emergency.status" label="Inactive">Inactive</el-radio>
                    <el-radio v-model="emergency.status" label="Complete">Complete</el-radio>
                  </div>
                  <div class="button-emergency-wrapper"></div>
                </form>
                <div style="text-align: right; margin: 0">
                  <el-button
                    type="primary"
                    icon="el-icon-upload"
                    circle
                    v-on:click="updateEmergency(emergency.id, emergency.name, emergency.location, emergency.status)"
                  ></el-button>
                </div>
              </el-popover>
              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                v-on:click="deleteEmergency(emergency.id)"
                style="margin-left:3px;"
              ></el-button>
            </div>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <el-pagination style="margin-top:5px;" layout="prev, pager, next" :total="1"></el-pagination>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";
import axios from "axios";
export default {
  components: { LMap, LTileLayer, LMarker },
  data() {
    return {
      value: "Active",
      visible: false,
      activeName: "1",
      emergencies: [],
      emergencyID: "",
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",

      zoom: 6,
      center: [-33.4489, -70.6693],
      icon: L.icon({
        iconUrl: "https://i.imgur.com/fmK9aII.png",
        iconSize: [32, 37],
        iconAnchor: [16, 37]
      })
    };
  },
  methods: {
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
.map {
  height: 600px;
  width: 100%;
}
</style>