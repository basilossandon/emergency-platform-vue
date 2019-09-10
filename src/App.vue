<template>
  <div id="app">
    <nav>
      <vue-navigation-bar
          :options="navbarOptions"
          @vnb-item-clicked="vnbItemClicked"
        >
        </vue-navigation-bar>
      <!-- 
      Routers viejos
      <router-link to="/emergencies">Todas las emergencias</router-link>
      <router-link to="/new-dog"> Nuevo perro</router-link>
      -->
    </nav>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      navbarOptions: {
        elementId: "main-navbar",
        isUsingVueRouter: true,
        mobileBreakpoint: 992,
        brandImagePath: "./",
        brandImage: require("../src/assets/images/lockup-color.png"),
        brandImageAltText: "brand-image",
        collapseButtonImageOpen: require("../src/assets/images/collapse-menu-dark.png"),
        collapseButtonImageClose: require("../src/assets/images/times.png"),
        showBrandImageInMobilePopup: true,
        ariaLabelMainNav: "Main Navigation",
        tooltipAnimationType: "shift-away",
        menuOptionsLeft: [
          {
            type: "link",
            text: "Emergencias",
                        subMenuOptions: [
              {
                isLinkAction: true,
                type: "link",
                text: "Emergencies",
                subText:
                  "See ongoing emergencies!",
                path: "./emergencies"
              },
              {
                type: "hr"
              },
              {
                type: "link",
                text: "Volunteers",
                subText: "View listed volunteers!",
                path: "./volunteers"
              },   
                         {
                type: "hr"
              },          
              {
                type: "link",
                text: "Nuevo Perro",
                subText: "Agrega perros a la base de datos!",
                path: "./new-dog"
              },
              {
                type: "hr"
              },
              {
                type: "link",
                text: "Tasks",
                subText:
                  "See currently ongoing tasks.",
                path: "./tasks"
              }
            ]
          }

        ],
        menuOptionsRight: [
          {
            type: "button",
            text: "Signup",
            path: "./signup",
            class: "button-red"
          },
          {
            type: "button",
            text: "Login",
            path: "./login",
            iconRight:
              '<svg id="i-arrow-right" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"> <path d="M22 6 L30 16 22 26 M30 16 L2 16" /> </svg>'
          }
        ]
      }
    };
  },
  methods: {
    vnbItemClicked(text) {
      if (text === "About") {
        alert("'About' was selected.");
      }
    }

  }
};
</script>

<style lang="scss">

</style>
