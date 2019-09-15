<template>
  <div class="new-task">
    <h1>Nueva emergencia</h1>
    <form>
      <div class="form-item">
        <label for="name">Nombre</label>
        <input id="name" type="text" v-model="emergency.name"/>
        <label for="location">Ubicación</label>
        <input id="location" type="text" v-model="emergency.location"/>
        <label for="status">Estado</label>
        <input id="status" type="text" v-model="emergency.status" />
      </div>
      <button type="button" @click="save">Guardar</button>
    </form>
    <div v-if="message.length>0" class="form-message">
      {{message}}
    </div>
    <div class="view-model">
      Name: {{emergency.name}} <br>
      Location: {{emergency.location}} <br>
      Status: {{emergency.status}}
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
