<template>
  <div class="navWrapper">
    <div id="menu" :class="{ active: isActive }">
      <el-menu mode="horizontal" @select="handleSelect" :router="true">
        <el-menu-item id="logo" index="/" :route="{ name: 'home'}">
          <img src="../assets/images/logo.png" height="21px" width="180px" />
        </el-menu-item>
        <el-menu-item index="/emergencies" :route="{ name: 'emergency' }">
          <i class="el-icon-warning"></i>Emergencies
        </el-menu-item>
        <el-menu-item index="/tasks" :route="{ name: 'task' }">
          <i class="el-icon-s-order"></i>Tasks
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-s-custom"></i>Volunteers
          </template>
          <el-menu-item index="/volunteers" :route="{ name: 'volunteer' }">
            <i class="el-icon-s-custom"></i>Volunteers
          </el-menu-item>
          <el-menu-item index="/volunteers/search" :route="{ name: 'volunteerSearch' }">
            <i class="el-icon-search"></i>Search
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="/import" :route="{ name: 'import' }">
          <i class="el-icon-upload"></i>Import
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-circle-plus"></i>Add
          </template>
          <el-menu-item index="/new-emergency" :route="{ name: 'new-emergency' }">Add emergency</el-menu-item>
          <el-menu-item index="/new-task" :route="{ name: 'new-task' }">Add tasks</el-menu-item>
          <el-menu-item index="/new-volunteer" :route="{ name: 'new-volunteer' }">Add volunteer</el-menu-item>
        </el-submenu>
        <div class="right-menu">
          <el-dropdown
            class="avatar-container"
            trigger="click"
            v-on:command="handleNavDropdownCommand"
          >
            <div class="avatar-wrapper">
              <el-avatar icon="el-icon-user-solid"></el-avatar>
              <i class="el-icon-caret-bottom" />
            </div>
            <el-dropdown-menu slot="dropdown" class="user-dropdown">
              <router-link to="/">
                <el-dropdown-item>Home</el-dropdown-item>
              </router-link>
              <router-link to="/user">
                <el-dropdown-item>Account</el-dropdown-item>
              </router-link>
              <router-link to="/account">
                <el-dropdown-item>Settings</el-dropdown-item>
              </router-link>
              <el-dropdown-item command="logout" divided>Logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
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
  components: {},
  data() {
    return {
      activeIndex: 1,
      isActive: false
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    select: function() {
      this.isActive = !this.isActive;
    },
    handleNavDropdownCommand: function(command) {
      if (command == "logout") {
        this.$confirm("Are you sure you want to logout?", "Logout", {
          confirmButtonText: "Yes",
          cancelButtonText: "No",
          type: "info"
        }).then(
          function() {
            this.$message("You have succesfully logged out!");
          }.bind(this)
        );
      }
    }
  }
};
</script>

<style scoped>
.right-menu {
  float: right;
  transform: translateY(15%);

  height: 100%;
  line-height: 50px;
  margin-right: 5px;
}
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
    transition: all 0.3s ease-out;
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
      transition: all 0.3s ease-out;
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
      transition: all 0.5s ease-out;

      @include sm-menu;
    }
  }
}
</style>
