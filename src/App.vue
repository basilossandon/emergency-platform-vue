<template>
  <div id="app">
    <nav>
      <router-link to="/emergencies">Todas las emergencias</router-link>
    </nav>
    <!--<img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'app',
  components: {
  }
}
</script>

<style lang="scss">

</style>
