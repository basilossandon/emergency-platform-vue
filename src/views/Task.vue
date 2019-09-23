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
                :src="'https://loremflickr.com/160/120/job?lock='+task.id"
              />
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content-text">
              <table>
                <tbody>
                  <tr>
                    <td>Capacity:</td>
                    <td>{{task.capacity}}</td>
                  </tr>
                  <tr>
                    <td>Status:</td>
                    <td>{{task.status}}</td>
                  </tr>
                  <tr>
                    <td>Emergency name:</td>
                    <td>{{task.emergency.name}}</td>
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
                    <label class="element-borders" for="name">Task name</label>
                    <el-input
                      class="element-borders"
                      id="name"
                      placeholder="e.g. Getting Supplies"
                      v-model="task.name"
                    ></el-input>
                    <label class="element-borders" for="capacity">Capacity</label>
                    <el-input
                      class="element-borders"
                      id="capacity"
                      placeholder="e.g 10"
                      v-model="task.capacity"
                    ></el-input>
                    <label for="status">Status</label>
                    <el-radio-group style="margin-top:15px;" v-model="task.status">
                      <el-radio v-model="task.status" label="Active">Active</el-radio>
                      <el-radio v-model="task.status" label="Inactive">Inactive</el-radio>
                      <el-radio v-model="task.status" label="Completed">Completed</el-radio>
                    </el-radio-group>
                  </div>
                  <div class="button-emergency-wrapper"></div>
                </form>
                <div style="text-align: right; margin: 0">
                  <el-button
                    type="primary"
                    icon="el-icon-upload"
                    circle
                    v-on:click="updateTask(task.id, task.name, task.capacity, task.status)"
                  ></el-button>
                </div>
              </el-popover>

              <el-button
                style="margin-left:5px;"
                type="danger"
                icon="el-icon-delete"
                circle
                v-on:click="deleteTask(task.id)"
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
          return axios.get(`http://localhost:4567/tasks`).then(response => {
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
    },
    updateTask(taskID, taskName, taskCapacity, taskStatus) {
      axios({
        method: "put",
        url: "http://localhost:4567/tasks/" + taskID,
        taskID: taskID,
        data: {
          name: taskName,
          capacity: taskCapacity,
          status: taskStatus
        },
        headers: { "Content-Type": "application/json" }
      })
        .then(() => {
          // when put is finished, the fire get
          return axios.get(`http://localhost:4567/tasks`).then(response => {
            this.tasks = response.data;
            this.$notify({
              title: "Task updated",
              message: "Task updated succesfully.",
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
