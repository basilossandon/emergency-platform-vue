<template>
  <div>
    <h1>
      <b>All Tasks</b>
    </h1>
    <el-collapse
      v-for="task in tasks"
      :key="task.id"
      :value="task.id"
      v-model="activeName"
      accordion
    >
      <el-collapse-item v-bind:title="task.name">
        <el-row>
          <el-col :span="4">
            <div class="grid-content-image">
              <img
                class="grid-content"
                style="border-radius:5px;   box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);"
                :src="'https://loremflickr.com/160/120/earthquake?lock='+task.id"
              />
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content-text">
              Capacity: {{task.capacity}}
              <br />
              Status: {{task.status}}
              Emergency: 
            </div>
          </el-col>
          <el-col :span="4">
            <div class="grid-content bottom">
              <el-button type="success" icon="el-icon-check" circle></el-button>
              <el-button type="primary" icon="el-icon-edit" circle></el-button>
              <el-button type="danger" icon="el-icon-delete" circle
                v-on:click="deleteTask(task.id)"
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
      tasks: [],
      taskID: "",
      axios: {
        credentials: false
      },
      emergencies: []
    };
  },
  methods: {
    deleteTask(taskID) {
      axios({
        method: "delete",
        url: "http://localhost:4567/tasks/" + taskID,
        taskID: taskID,
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios
            .get(`http://localhost:4567/tasks`)
            .then(response => {
              this.tasks = response.data;
              this.$notify({
                title: "Task removed",
                message: "Task removed succesfully.",
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
    axios.get(`http://localhost:4567/tasks`).then(response => {
      this.tasks = response.data;
    });

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
</style>
