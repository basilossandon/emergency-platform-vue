<template>
  <div id="app">
    <nav>
      <router-link to="/emergencies">Todas las emergencias</router-link>
    </nav>
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
