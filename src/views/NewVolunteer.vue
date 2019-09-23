<template>
  <div class="new-volunteer">
    <h1>New volunteer</h1>
    <form>
      <div class="form-item">
        <label class="element-borders" for="name">Volunteer name</label>
        <el-input
          class="element-borders"
          id="name"
          placeholder="e.g. John Smith"
          v-model="volunteer.name"
        ></el-input>
        <label class="element-borders" for="age">Age</label>
        <el-input
          class="element-borders"
          id="age"
          type="number"
          min="18"
          placeholder="e.g 25"
          v-model="volunteer.age"
        ></el-input>
        <label for="sex">Gender</label>
        <el-radio-group style="margin-top:15px;" v-model="volunteer.sex">
          <el-radio v-model="volunteer.sex" label="Male">Male</el-radio>
          <el-radio v-model="volunteer.sex" label="Female">Female</el-radio>
          <el-radio v-model="volunteer.sex" label="Other">Other</el-radio>
        </el-radio-group>
        <br />

        <span style="margin-top:20px;" class="demonstration">Motivation</span>
        <el-rate style="margin:2px;" v-model="value1"></el-rate>
        <span style="margin:2px;" class="demonstration">Strength</span>
        <el-rate style="margin:2px;" v-model="value2"></el-rate>
        <span style="margin:2px;" class="demonstration">Dextery</span>
        <el-rate style="margin:2px;" v-model="value3"></el-rate>
        <span style="margin:2px;" class="demonstration">Leadership</span>
        <el-rate style="margin:2px;" v-model="value4"></el-rate>
        <span style="margin:2px;" class="demonstration">Knowledge</span>
        <el-rate style="margin:2px;" v-model="value5"></el-rate>
      </div>
      <div class="button-volunteer-wrapper">
        <el-button type="primary" round icon="el-icon-upload" @click="save">Save</el-button>
      </div>
    </form>
  </div>
</template>
<script>
export default {
  data: function() {
    return {
      volunteer: {},
      message: "",
      value1: null,
      value2: null,
      value3: null,
      value4: null,
      value5: null,

      colors: ["#99A9BF", "#F7BA2A", "#FF9900"] // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
  methods: {
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
</style>
