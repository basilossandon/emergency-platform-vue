<template>
  <div class="new-dog">
    <h1>Nuevo perro</h1>
    <form>
      <div class="form-item">
        <label for="name">Nombre</label>
        <input id="name" type="text" v-model="dog.name" />
      </div>
      <button type="button" @click="save">Guardar</button>
    </form>
    <div v-if="message.length>0" class="form-message">
      {{message}}
    </div>
    <div class="view-model">
      Nombre: {{dog.name}}
    </div>
  </div>
</template>
<script>
export default{
  data:function(){
    return{
      dog:{},
      message:""
    }
  },
  methods:{
    save:async function(){
      this.message = "";
      if(this.dog.name=="" && user.password == ""){
        this.message = "Debe ingresar un nombre y password";
        return false;
      }
      try {
          let response = await this.$http.post('/appusers', this.dog);
          this.message = "Se ha agregado existosamente"
      } catch (e) {
        console.log('error',e)
        this.message= "Ha ocurrido un error"
      }
    }
  }
}
</script>
