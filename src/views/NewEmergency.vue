<template>
  <div class="new-task">
    <h1>New emergency</h1>
    <form>
      <div class="form-item">
        <label for="name">Name</label>
        <input id="name" type="text" v-model="emergency.name"/>
        <label for="location">Location</label>
        <input id="location" type="text" v-model="emergency.location"/>
        <label for="status">Status</label>
        <el-radio-group v-model="emergency.status">
          <el-radio v-model="emergency.status" label="Active">Active</el-radio>
          <el-radio v-model="emergency.status" label="Inactive">Inactive</el-radio>
        </el-radio-group>
      </div>
      <button type="button" @click="save">Save</button>
    </form>
    <div v-if="message.length>0" class="form-message">
      {{message}}
    </div>
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
    save:async function(){
      this.message = "";
      if(this.emergency.name==""){
        this.message = "Debe ingresar un nombre";
        return false;
      }
      if(this.emergency.capacity==""){
        this.message = "Debe ingresar la capacidad de la tarea";
        return false;
      }
      if(this.emergency.status==""){
        this.emergency.status = "Not assigned";
        console.log(this.emergency.status);
      }
      try {
          let response = await this.$http.post('/emergencies', this.emergency);
          this.message = "Emergency saved successfully"
          console.log(response);
      } catch (e) {
        console.log('error',e)
        this.message= "An error has occurred"
      }
    }
  }
}
</script>
