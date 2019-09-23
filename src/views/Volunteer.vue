<template>
  <div>
    <h1>
      <b>All volunteers</b>
    </h1>
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
                :src="'https://loremflickr.com/160/120/earthquake?lock='+volunteer.id"
              />
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content-text">
              Age:{{volunteer.age}}
              <br />
              Sex: {{volunteer.sex}}
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


              <el-button type="danger" icon="el-icon-delete" circle
                v-on:click="deleteVolunteer(volunteer.id)"
              ></el-button>
            </div>
          </el-col>
        </el-row>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      activeName: "1",
      volunteers: [],
      volunteerID: "",
      axios: {
        credentials: false
      }
    };
  },
  methods: {
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
    updateVolunteer(
      volunteerID,
      volunteerName,
      volunteerAge,
      volunteerSex
    ) {
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
            .get(`http://localhost:4567/volunteers`)
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
    axios.get(`http://localhost:4567/volunteers`).then(response => {
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
