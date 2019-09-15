<template>
  <div class="new-task">
    <h1>Registro voluntario</h1>
    <form>
      <div class="form-item">
        <label for="name">Nombre</label>
        <input id="name" type="text" v-model="volunteer.name"/>
        <label for="age">Edad</label>
        <input id="age" type="number" min="1" v-model="volunteer.age"/>
        <label for="sex">Sexo</label>
        <el-radio-group v-model="volunteer.sex">
          <el-radio v-model="volunteer.sex" label="H">Hombre</el-radio>
          <el-radio v-model="volunteer.sex" label="M">Mujer</el-radio>
          <el-radio v-model="volunteer.sex" label="Otro" >Otro</el-radio>
        </el-radio-group>
      </div>
      <button type="button" @click="save">Guardar</button>
    </form>
    <div v-if="message.length>0" class="form-message">
      {{message}}
    </div>
    <div class="view-model">
      Name: {{volunteer.name}} <br>
      Age: {{volunteer.age}} <br>
      Sex: {{volunteer.sex}}
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
