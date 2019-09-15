<template>
  <div class="new-task">
    <h1>New task</h1>
    <form>
      <div class="form-item">
        <label for="name">Name</label>
        <input id="name" type="text" v-model="task.name"/>
        <label for="capacity">Capacity</label>
        <input id="capacity" type="number" v-model="task.capacity"/>
        <label for="status">Status</label>
        <el-radio-group v-model="task.status">
          <el-radio v-model="task.status" label="Active">Active</el-radio>
          <el-radio v-model="task.status" label="Inactive">Inactive</el-radio>
          <el-radio v-model="task.status" label="Completed">Completed</el-radio>
        </el-radio-group>
      </div>
      <button type="button" @click="save">Save</button>
    </form>
    <div v-if="message.length>0" class="form-message">
      {{message}}
    </div>
    <div class="view-model">
      Name: {{task.name}} <br>
      Capacity: {{task.capacity}} <br>
      Status: {{task.status}}

    </div>
  </div>
</template>
<script>
export default{
  data:function(){
    return{
      task:{},
      message:""
    }
  },
  methods:{
    save:async function(){
      this.message = "";
      if(this.task.name==""){
        this.message = "Debe ingresar un nombre";
        return false;
      }
      if(this.task.capacity==""){
        this.message = "Debe ingresar la capacidad de la tarea";
        return false;
      }
      if(this.task.status==""){
        this.task.status = "Not assigned";
      }
      try {
          console.log(this.task.status);
          let response = await this.$http.post('/tasks', this.task);
          console.log(response);
          this.message = "Se ha agregado existosamente"
      } catch (e) {
        console.log('error',e)
        this.message= "Ha ocurrido un error"
      }
    }
  }
}
</script>
