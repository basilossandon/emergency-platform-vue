<template>
  <div>
    <h1>
      <b>All volunteers</b>
    </h1>
    <l-map style="height: 350px; width: 100%" :zoom="zoom" :center="center">
      <l-tile-layer :url="url"></l-tile-layer>
      <l-marker
        v-for="volunteer in volunteers"
        :key="volunteer.id"
        :lat-lng="[volunteer.latitude, volunteer.longitude]"
      ></l-marker>
    </l-map>
    <el-collapse
      v-for="volunteer in volunteers"
      :key="volunteer.id"
      :value="volunteer.id"
      v-model="activeName"
      accordion
    >
      <el-collapse-item v-bind:title="volunteer.name">
        <el-row>
          <el-col :span="4">
            <div class="grid-content-image">
              <img
                class="grid-content"
                style="border-radius:5px;   box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);"
                :src="'https://loremflickr.com/160/120/cat?lock='+volunteer.id"
              />
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content-text">
              <table style="width: 207.017px;">
                <tbody>
                  <tr style="height: 23px;">
                    <td style="height: 23px; width: 92px;">&nbsp; Name:</td>
                    <td style="height: 23px; width: 113.017px;">{{volunteer.name}}</td>
                  </tr>
                  <tr style="height: 23px;">
                    <td style="height: 23px; width: 92px;">&nbsp;RUT:</td>
                    <td style="height: 23px; width: 113.017px;">{{volunteer.rut}}</td>
                  </tr>
                  <tr style="height: 23px;">
                    <td style="height: 23px; width: 92px;">&nbsp;Sex:</td>
                    <td style="height: 23px; width: 113.017px;">{{volunteer.sex}}</td>
                  </tr>
                  <tr style="height: 23px;">
                    <td style="height: 23px; width: 92px;">&nbsp;Email:</td>
                    <td style="height: 23px; width: 113.017px;">{{volunteer.email}}</td>
                  </tr>
                  <tr style="height: 23px;">
                    <td style="height: 23px; width: 92px;">&nbsp;Position:</td>
                    <td
                      style="height: 23px; width: 113.017px;"
                    >{{volunteer.latitude}},{{volunteer.longitude}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="grid-content bottom">
              <el-popover trigger="click" ref="popover" placement="top" width="160">
                <el-button type="primary" icon="el-icon-edit" circle slot="reference"></el-button>
                <form>
                  <div class="form-item">
                    <label class="element-borders" for="name">Volunteer name</label>
                    <el-input
                      class="element-borders"
                      id="name"
                      placeholder="e.g. Amelia Pond"
                      v-model="volunteer.name"
                    ></el-input>
                    <label class="element-borders" for="age">Age</label>
                    <el-input
                      class="element-borders"
                      id="age"
                      placeholder="e.g 22"
                      v-model="volunteer.age"
                    ></el-input>
                    <label for="sex">Gender</label>
                    <el-radio-group style="margin-top:15px;" v-model="volunteer.sex">
                      <el-radio v-model="volunteer.sex" label="Male">Male</el-radio>
                      <el-radio v-model="volunteer.sex" label="Female">Female</el-radio>
                      <el-radio v-model="volunteer.sex" label="Other">Other</el-radio>
                    </el-radio-group>
                  </div>
                  <div class="button-emergency-wrapper"></div>
                </form>
                <div style="text-align: right; margin: 0">
                  <el-button
                    type="primary"
                    icon="el-icon-upload"
                    circle
                    v-on:click="updateVolunteer(volunteer.id, volunteer.name, volunteer.age, volunteer.sex)"
                  ></el-button>
                </div>
              </el-popover>
              <el-button
                style="margin-left: 5px;"
                type="danger"
                icon="el-icon-delete"
                circle
                v-on:click="deleteVolunteer(volunteer.id)"
              ></el-button>
            </div>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
    <div class="block">
      <span class="demonstration"></span>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 30, 100,200,400,700]"
        :page-size.sync="pageSize"
        layout="sizes, prev, pager, next"
        :total="700"
      ></el-pagination>
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
      activeName: "1",
      volunteers: [],
      volunteerID: "",
      axios: {
        credentials: false
      },
      currentPage: 0,
      pageSize: 10,
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",

      zoom: 3,
      center: [-33.4489, -70.6693],
      marker: {
        position: { lat: -33.4489, lng: -70.6693 }
      }
    };
  },
  methods: {
    handleSizeChange(val) {
      axios
        .get(
          `http://localhost:4567/volunteers/pages?page=` +
            this.currentPage +
            "&size=" +
            this.pageSize
        )
        .then(response => {
          this.volunteers = response.data;
        });
      console.log(`${val} items per page`);
    },
    handleCurrentChange(val) {
      axios
        .get(
          `http://localhost:4567/volunteers/pages?page=` +
            this.currentPage +
            "&size=" +
            this.pageSize
        )
        .then(response => {
          this.volunteers = response.data;
        });
      console.log(`current page: ${val}`);
    },
    deleteVolunteer(volunteerID) {
      axios({
        method: "delete",
        url: "http://localhost:4567/volunteers/" + volunteerID,
        volunteerID: volunteerID,
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/volunteers`)
            .then(response => {
              this.volunteers = response.data;
              this.$notify({
                title: "Volunteer removed",
                message: "Volunteer removed succesfully.",
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
    updateVolunteer(volunteerID, volunteerName, volunteerAge, volunteerSex) {
      axios({
        method: "put",
        url: "http://localhost:4567/volunteers/" + volunteerID,
        volunteerID: volunteerID,
        data: {
          name: volunteerName,
          age: volunteerAge,
          sex: volunteerSex
        },
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/volunteers/`)
            .then(response => {
              this.volunteers = response.data;
              this.$notify({
                title: "Volunteer updated",
                message: "Volunteer updated succesfully.",
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
    axios.get(`http://localhost:4567/volunteers/pages`).then(response => {
      console.log(response);
      this.volunteers = response.data;
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
