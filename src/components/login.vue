<template>
  <div>
    <h2>Login</h2>
    <form v-on:submit="login">
      <h2>Email:</h2>
      <el-input
        class="element-borders"
        id="email"
        placeholder="e.g. pablo.chille@usach.cl"
        prefix-icon="el-icon-user"
      ></el-input>
      <h2>Password:</h2>
      <el-input
        class="element-borders"
        id="password"
        type="password"
        placeholder="**********"
        prefix-icon="el-icon-lock"
      ></el-input>

      <div>
        <el-checkbox style="width:26%; margin-top: 14px;" v-model="checked1" border>Remember me</el-checkbox>
        <el-button
          type="primary"
          style="width: 73%; margin-top: 15px; float:right;"
          icon="el-icon-unlock"
        >Login</el-button>
      </div>
            <div>
        
        <router-link to="/register" >
        <el-button
          type="default"
          style="width: 100%; margin-top: 15px;"
          icon="el-icon-user"
        >Register</el-button></router-link>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      if (this.password.length > 0) {
        this.$http
          .post("http://localhost:4567/login", {
            email: this.email,
            password: this.password
          })
          .then(response => {
            let is_admin = response.data.user.is_admin;
            localStorage.setItem("user", JSON.stringify(response.data.user));
            localStorage.setItem("jwt", response.data.token);

            if (localStorage.getItem("jwt") != null) {
              this.$emit("loggedIn");
              if (this.$route.params.nextUrl != null) {
                this.$router.push(this.$route.params.nextUrl);
              } else {
                if (is_admin == 1) {
                  this.$router.push("admin");
                } else {
                  this.$router.push("dashboard");
                }
              }
            }
          })
          .catch(function(error) {
            console.error(error.response);
          });
      }
    }
  }
};
</script>