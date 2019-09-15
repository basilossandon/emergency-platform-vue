<template>
  <div class="navWrapper">
    <div id="menu" :class="{ active: isActive }">
      <el-menu mode="horizontal" @select="handleSelect" :router="true">
      <el-menu-item id="logo" index="/" :route="{ name: 'emergency'}">
      <img src="../assets/images/logo.png" height="21px" width="180px"/>
      </el-menu-item>
      <el-menu-item index="/emergencies" :route="{ name: 'emergency' }">
        Emergencies
      </el-menu-item>
      <el-menu-item index="/new-task" :route="{ name: 'new-task' }">
        Add tasks
      </el-menu-item>
      <el-menu-item index="/new-emergency" :route="{ name: 'new-emergency' }">
        Add emergency
      </el-menu-item>
      <el-menu-item index="/new-volunteer" :route="{ name: 'new-volunteer' }">
        Add volunteer
      </el-menu-item>
      </el-menu>
    </div>
    <div id="toggle" @click="select()">
      <div class="span" id="top" :class="{ active: isActive }"></div>
      <div class="span" id="middle" :class="{ active: isActive }"></div>
      <div class="span" id="bottom" :class="{ active: isActive }"></div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    
  },
  data() {
    return {
      activeIndex: 1,
      isActive: false
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    select: function() {
      this.isActive = !this.isActive;
    }
  }
}
</script>

<style scoped>
#app {

  .logo-header {
    padding-left: 15px;
    padding-right: 50px;
    z-index: 100;
  }

  .navWrapper {
    width: 100%;
    position: fixed;
    top: 0;
  }

  #logo.el-menu-item.is-active {
    border-bottom-color: transparent;
  }

  #toggle {
    position: absolute;
    right: 20px;
    top: 14px;
    z-index: 999;
    width: 40px;
    height: 40px;
    cursor: pointer;
    float: right;
    transition: all .3s ease-out;
    visibility: hidden;
    opacity: 0;
  }

  #toggle .span {
    border-radius: 10px;
    background: $nav-toggle-color;
    transition: all 0.3s ease-out;
    backface-visibility: hidden;
  }

  #top.span.active {
    transform: rotate(45deg) translateX(3px) translateY(5px);
  }

  #middle.span.active {
    opacity: 0;
  }

  #bottom.span.active {
    transform: rotate(-45deg) translateX(8px) translateY(-10px);
  }

  @media only screen and (max-width: $screen-md-min) {

    .logo-header {
      padding: 0;
    }

    #toggle {
      visibility: visible;
      opacity: 1;
      margin-top: 6px;
    }

    #toggle .span {
      height: 4px;
      margin: 5px 0;
      transition: all .3s ease-out;
      backface-visibility: visible;
      visibility: visible;
      opacity: 1;
    }

    #menu .el-menu-item {
      display: none;
    }

    #menu.active {
      margin: 70px 0;
      visibility: visible;
      opacity: 0.98;
      transition: all .5s ease-out;

      @include sm-menu;
    }
  }

}
</style>