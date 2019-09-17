<template>
  <div class="new-emergency">
    <h1>New emergency</h1>
    <form>
      <div class="form-item">
        <label class="element-borders" for="name">Emergency name</label>
        <el-input class="element-borders" id="name" placeholder="e.g. Thailand Tsunami" v-model="emergency.name"></el-input>
        <label class="element-borders" for="location">Location</label>
        <el-input class="element-borders" id="location" placeholder="e.g Thailand" v-model="emergency.location"></el-input>
        <label for="status">Status</label>
        <el-radio-group style="margin-top:15px;" v-model="emergency.status">
          <el-radio v-model="emergency.status" label="Active">Active</el-radio>
          <el-radio v-model="emergency.status" label="Inactive">Inactive</el-radio>
        </el-radio-group>
      </div>
      <div class="button-emergency-wrapper">
      <el-button type="primary" round icon="el-icon-upload" @click="save"> Save </el-button>
      </div>
      </form>
  </div>
</template>
<script>
export default{
  data:function(){
    return{
      emergency:{},
      message:""
    }
  },
  methods:{
    save:
    async function(){
      this.message = "";
      if(this.emergency.name==""){
        this.message = "You must enter an emergency name";
        this.$notify({
          title: 'Warning',
          message: 'Emergency name missing!',
          type: 'warning'
        });
        return false;
      }
      if(this.emergency.location==""){
        this.message = "You must enter the capacity of the emergency";
        this.$notify({
          title: 'Warning',
          message: 'Emergency location missing!',
          type: 'warning'
        });

        return false;
      }
      if(this.emergency.status==""){
        this.$notify({
          title: 'Warning',
          message: 'Emergency status not selected!',
          type: 'warning'
        });
        return false;        
      }
      try {
          let response = await this.$http.post('/emergencies', this.emergency);
          this.message = "Emergency saved successfully"
          console.log(response);
          this.$message({
          message: 'Emergency succesfully created.',
          type: 'success'});
      } catch (e) {
        console.log('error',e)
        this.message= "An error has occurred"
        this.$notify({
          title: 'Warning',
          message: 'Please include name, location and status.',
          type: 'warning'
        });
      }
    }
  }
}
</script>

<style scoped>
.new-emergency{
      box-sizing: border-box;
    padding: 8px 0 15px;
    position: relative;
}
.button-emergency-wrapper {
    align-items: center;
    bottom: calc(-100px/2);
    display: flex;
    flex-direction: column;
    left: 0;
    margin: 0 auto;
    pointer-events: none;
    position: absolute;
    right: 0;
}
.element-borders{
  margin-top: 3px;
  margin-bottom:3px;
}


</style>