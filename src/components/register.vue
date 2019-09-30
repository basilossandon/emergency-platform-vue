<template>
  <div>
    <h4>Register</h4>
    <form>
      <h1>Name</h1>
      <div>
        <el-input
          class="element-borders"
          id="email"
          placeholder="e.g. Pablo Chille"
          prefix-icon="el-icon-user"
          v-model="name"
        ></el-input>
      </div>

      <h1>E-Mail Address</h1>
      <div>
        <el-input
          class="element-borders"
          id="email"
          placeholder="e.g. pablo.chille@usach.cl"
          prefix-icon="el-icon-message"
          v-model="email"
        ></el-input>
      </div>

      <h1>Password</h1>
      <div>
        <el-input
          placeholder="Please input password"
          v-model="password"
          prefix-icon="el-icon-lock"
          show-password
        ></el-input>
      </div>

      <h1>Confirm Password</h1>
      <div>
        <el-input
          placeholder="Please input password"
          prefix-icon="el-icon-lock"
          show-password
          v-model="pw_confirmation"
        ></el-input>
      </div>

      <div>
        <el-button
          style="margin-top:15px; width: 100%;"
          type="primary"
          @click="handleSubmit"
        >Register</el-button>
      </div>
    </form>
  </div>
</template>

<script>
import router from "../router";
import axios from "axios";
export default {
  props: ["nextUrl"],
  data() {
    return {
      name: "",
      email: "",
      password: "",
      pw_confirmation: "",
      is_admin: null
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();

      if (
        this.password === this.pw_confirmation &&
        this.password.length > 0
      ) {
        let url = "http://localhost:4567/register";
        if (this.is_admin != null || this.is_admin == 1)
          url = "http://localhost:4567/register-admin";
        this.$http
          .post(url, {
            name: this.name,
            email: this.email,
            password: this.password,
            is_admin: this.is_admin
          })
          .then(response => {
            localStorage.setItem("user", JSON.stringify(response.data.user));
            localStorage.setItem("jwt", response.data.token);

            if (localStorage.getItem("jwt") != null) {
              this.$emit("loggedIn");
              if (this.$route.params.nextUrl != null) {
                this.$router.push(this.$route.params.nextUrl);
              } else {
                this.$router.push("/");
              }
            }
          })
          .catch(error => {
            console.error(error);
          });
      } else {
        this.password = "";
        this.passwordConfirm = "";
        return alert("Passwords do not match");
      }
    }
  }
};
</script>