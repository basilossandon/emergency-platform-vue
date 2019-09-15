<template>
  <div class="new-task">
    <h1>New volunteer</h1>
    <form>
      <div class="form-item">
        <label for="name">Name</label>
        <input id="name" type="text" v-model="volunteer.name"/>
        <label for="age">Age</label>
        <input id="age" type="number" min="18" v-model="volunteer.age"/>
        <label for="sex">Sex</label>
        <el-radio-group v-model="volunteer.sex">
          <el-radio v-model="volunteer.sex" label="H">Male</el-radio>
          <el-radio v-model="volunteer.sex" label="M">Female</el-radio>
          <el-radio v-model="volunteer.sex" label="Otro" >Other</el-radio>
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
      volunteer:{},
      message:"",
      radio: '1'
    }
  },
  methods:{
    save:async function(){
      this.message = "";
      if(this.volunteer.name==""){
        this.message = "Debe ingresar un nombre";
        return false;
      }
      if(this.volunteer.capacity==""){
        this.message = "Debe ingresar la capacidad de la tarea";
        return false;
      }
      if(this.volunteer.status==""){
        this.volunteer.status = "Not assigned";
        console.log(this.volunteer.status);
      }
      try {
          let response = await this.$http.post('/volunteers', this.volunteer);
          this.message = "Se ha agregado existosamente"
          console.log(response);
      } catch (e) {
        console.log('error',e)
        this.message= "Ha ocurrido un error"
      }
    }
  }
}
</script>
