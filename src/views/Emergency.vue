<template>
  <div>
    <h1>
      <b>All emergencies</b>
    </h1>

    <div class="container">
      <div class="row">
        <l-map style="height: 600px; margin-top:20px" :zoom="zoom" :center="center">
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
              {{emergency.name}}
              <br />
            </l-popup>
          </l-marker>
        </l-map>
      </div>
      <div class="row">
        <div class="emergencyData">
          <el-divider content-position="left">
            <h2>{{loadedEmergency.name}}</h2>
          </el-divider>
          
          <el-row :span="4">

            <el-col :span="8">
              <div class="grid-content-image">
                <img
                  class="grid-content"
                  style="border-radius:5px;   box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);"
                  :src="'https://loremflickr.com/160/120/earthquake?lock='+this.loadedEmergency.id"
                />
              </div>
            </el-col>
            
            <el-col :span="8">
              <div class="grid-content-text">
                <el-row>
                  <el-col :span="16">Name:</el-col>
                  <el-col :span="2">{{loadedEmergency.name}}</el-col>
                </el-row>
                <el-row>
                  <el-col :span="16">Status:</el-col>
                  <el-col :span="2">{{loadedEmergency.status}}</el-col>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </div>

        <div class="tasksOfEmergency" style="height: 20%">
          <el-divider content-position="left">
            <h2>Tasks</h2>
          </el-divider>
        </div>

        <div class="volunteersOfEmergency">
          <el-divider content-position="left">
            <h2>Volunteers</h2>
          </el-divider>
        </div>
      </div>

      <div class="grid-content bottom">
        <el-tooltip class="item" effect="dark" content="Assign task" placement="top-start">
          <el-popover placement="top" width="160" v-model="visible">
            <p>Are you sure to delete this?</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="visible = false">cancel</el-button>
              <el-button type="primary" size="mini" @click="visible = false">confirm</el-button>
            </div>
            <el-button
              slot="reference"
              type="success"
              icon="el-icon-plus"
              circle
              style="margin-right:3px;"
            ></el-button>
          </el-popover>
        </el-tooltip>

        <el-popover trigger="click" ref="popover" placement="top" width="160">
          <el-button type="primary" icon="el-icon-edit" circle slot="reference"></el-button>
          <form>
            <div class="form-item">
              <label class="element-borders" for="name">{{loadedEmergency.name}}</label>
              <el-input
                class="element-borders"
                id="name"
                placeholder="e.g. Thailand Tsunami"
                v-model="loadedEmergency.name"
              ></el-input>
              <label class="element-borders" for="location">Location</label>
              <el-input
                class="element-borders"
                id="location"
                placeholder="e.g Thailand"
                v-model="this.loadedEmergency.location"
              ></el-input>
              <label for="status">Status</label>
              <el-radio v-model="this.loadedEmergency.status" label="Active">Active</el-radio>
              <el-radio v-model="this.loadedEmergency.status" label="Inactive">Inactive</el-radio>
              <el-radio v-model="this.loadedEmergency.status" label="Complete">Complete</el-radio>
            </div>
            <div class="button-this.Emergency-wrapper"></div>
          </form>
          <div style="text-align: right; margin: 0">
            <el-button
              type="primary"
              icon="el-icon-upload"
              circle
              v-on:click="updateEmergency(loadedEmergency.id, loadedEmergency.name, loadedEmergency.location, loadedEmergency.status)"
            ></el-button>
          </div>
        </el-popover>
        <el-button
          type="danger"
          icon="el-icon-delete"
          circle
          v-on:click="deleteEmergency(loadedEmergency.id)"
          style="margin-left:3px;"
        ></el-button>

        <el-popover
          placement="top"
          title="Get volunteers"
          width="350"
          trigger="click"
          content="this is content, this is content, this is content"
        >
          <el-button
            type="warning"
            icon="el-icon-place"
            circle
            style="margin-left:3px;"
            slot="reference"
          ></el-button>
          <div class="block">
            <span class="demonstration">Get volunteers in a determined radius:</span>
            <el-slider v-model="circle.radius"></el-slider>

            <el-divider content-position="left">Strength</el-divider>
            <el-rate
              v-model="strength"
              style="margin:2px;"
              :max="10"
              show-score
              text-color="#ff9900"
              score-template="{value} points"
            ></el-rate>
            <el-divider content-position="left">Dextery</el-divider>
            <el-rate
              v-model="dextery"
              style="margin:2px;"
              :max="10"
              show-score
              text-color="#ff9900"
              score-template="{value} points"
            ></el-rate>
            <el-divider content-position="left">Knowledge</el-divider>
            <el-rate
              v-model="knowledge"
              style="margin:2px;"
              :max="10"
              show-score
              text-color="#ff9900"
              score-template="{value} points"
            ></el-rate>
            <el-divider content-position="left">Motivation</el-divider>
            <el-rate
              v-model="motivation"
              style="margin:2px;"
              :max="10"
              show-score
              text-color="#ff9900"
              score-template="{value} points"
            ></el-rate>
            <el-divider content-position="left">Leadership</el-divider>
            <el-rate
              v-model="leadership"
              style="margin:2px;"
              :max="10"
              show-score
              text-color="#ff9900"
              score-template="{value} points"
            ></el-rate>

            <el-button
              type="success"
              icon="el-icon-place"
              v-on:click="getVolunteersAround(loadedEmergency.id)"
              style="margin-left:3px; margin-top:15px; width:100%;"
            >Obtain volunteers</el-button>
          </div>
        </el-popover>
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
      value: "Active",
      visible: false,
      activeName: "1",
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
        "https://cartodb-basemaps-{s}.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png",

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
  position: relative;
  grid-template-columns: 45% 55%;
  grid-template-rows: 2fr;
  grid-column-gap: 20px;
  grid-row-gap: 20px;
  justify-items: stretch;
  align-items: stretch;
}
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
  width: 25%;
}
.grid-content-crud {
  width: 15%;
}
.map {
  height: 600px;
  width: 100%;
}
.grid-content {
  position: bottom;
}
</style>