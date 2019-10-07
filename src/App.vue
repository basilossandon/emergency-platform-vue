<template>
  <div class="app">
    <NavBar class="app__navbar"></NavBar>
    <div class="main-container">
      <div class="content-wrapper">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import "element-ui/lib/theme-chalk/reset.css";


export default {
  name: "app",
  components: {
    NavBar
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0px;
}

.main-container {
  text-align: center;
  margin-top: 25px;
  margin-left: auto;
  margin-right: auto;
  min-height: 10vh;
  max-width: 900px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  -webkit-border-radius: 30px;
  border-radius: 15px;
}
.content-wrapper {
  padding: 30px;
}
</style>