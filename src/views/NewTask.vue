        <template>
  <div class="new-task">
    <h1>New task</h1>
    <form>
      <div class="form-item">
        <label class="element-borders" for="name">Task Name</label>
        <el-input
          class="element-borders"
          id="name"
          placeholder="e.g. Getting med kits"
          v-model="task.name"
        ></el-input>
        <label class="element-borders" for="capacity">Capacity</label>
        <el-input
          class="element-borders"
          id="capacity"
          type="number"
          min="0"
          placeholder="e.g 25"
          v-model="task.capacity"
        ></el-input>
        <label class="element-borders" for="capacity">Assign to emergency:</label>
        <el-select
          class="select-size"
          v-model="taskassignations.emergency"
          placeholder="e.g Thailand Tsunami"
        >
          <el-option
            v-for="emergency in emergencies"
            :key="emergency.id"
            :label="emergency.name"
            :value="emergency.id"
          ></el-option>
        </el-select>
        <label class="element-borders" for="capacity">Volunteer in charge:</label>

        <el-select class="select-size" v-model="task.volunteer_id" placeholder="e.g Max Steel">
          <el-option
            v-for="volunteer in volunteers"
            :key="volunteer.id"
            :label="volunteer.name"
            :value="volunteer.id"
          ></el-option>
        </el-select>

        <label for="status">Status</label>
        <el-radio-group style="margin-top:15px;" v-model="task.status">
          <el-radio v-model="task.status" label="Active">Active</el-radio>
          <el-radio v-model="task.status" label="Inactive">Inactive</el-radio>
          <el-radio v-model="task.status" label="Completed">Completed</el-radio>
        </el-radio-group>
      </div>
      <div class="button-task-wrapper">
        <el-button type="primary" round icon="el-icon-upload" @click="save">Save</el-button>
      </div>
    </form>

    <div class="view-model">
      Name: {{task.name}}<br/>
      Capacity: {{task.capacity}}<br/>
      Status: {{task.status}}<br/>
      taskassignations emergency: {{taskassignations.emergency}}<br/>
    </div>
  </div>
</template>
<script>
export default {
  data: function() {
    return {
      task: {},
      value: "",
      taskassignations: {},
      volunteers: [],
      message: "",
      radio: "",
      emergencies: [],
      assignEmergencyToTask: "",
      assignVolunteerToTask: ""
    };
  },
  methods: {
    save: async function() {
      this.message = "";
      if (this.task.name == "") {
        this.$notify({
          title: "Warning",
          message: "Task name missing!",
          type: "warning"
        });
        return false;
      }
      if (this.task.capacity == "") {
        this.$notify({
          title: "Warning",
          message: "Task capacity missing!",
          type: "warning"
        });
        return false;
      }
      if (this.task.status == "") {
        this.$notify({
          title: "Warning",
          message: "Task status not selected!",
          type: "warning"
        });
        return false;
      }
      try {
        console.log(this.task);
        let response = await this.$http.post("tasks/emergencies/"+this.taskassignations.emergency, this.task);
        this.message = "Task saved successfully";
        console.log(response);
        this.$message({
          message: "Task succesfully created.",
          type: "success"
        });
      } catch (e) {
        console.log("error", e);
        this.message = "An error has ocurred";
        this.$notify({
          title: "Warning",
          message: "Please include name, capacity and status.",
          type: "warning"
        });
      }
    },
    getData: async function() {
      try {
        let response = await this.$http.get(`/emergencies`);
        let response2 = await this.$http.get(`/volunteers`);
        this.volunteers = response2.data;
        this.emergencies = response.data;
        console.log("headers", response.headers);
      } catch (e) {
        console.log("error", e);
      }
    }
  },
  created: function() {
    this.getData();
  }
};
</script>

<style scoped>
.new-task {
  box-sizing: border-box;
  padding: 8px 0 15px;
  position: relative;
}
.button-task-wrapper {
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
.select-size {
  width: 100%;
}
</style>
