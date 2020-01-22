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
          v-model="appuser.name"
        ></el-input>
      </div>

      <h1>E-Mail Address</h1>
      <div>
        <el-input
          class="element-borders"
          id="email"
          placeholder="e.g. pablo.chille@usach.cl"
          prefix-icon="el-icon-message"
          v-model="appuser.email"
          type="email"
        ></el-input>
      </div>

      <h1>Password</h1>
      <div>
        <el-input
          placeholder="Please input password"
          v-model="appuser.password"
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
          v-model="passwordConfirm"
        ></el-input>
      </div>
      <el-switch
        style="display: block; margin-top: 15px;"
        v-model="appuser.role"
        active-color="#13ce66"
        inactive-color="#409EFF"
        active-text="Coordinator"
        inactive-text="Volunteer"
        active-value="coordinator"
        inactive-value="volunteer"
      ></el-switch>

      <div>
        <el-button style="margin-top:15px; width: 100%;" type="primary" @click="save">Register</el-button>
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
      passwordConfirm: "",
      appuser: {}
    };
  },
  methods: {
    save: async function() {
      try {
        if (
          this.appuser.password != this.passwordConfirm &&
          this.appuser.password.length > 0
        ) {
          this.$message({
            message: "Password do not match.",
            type: "warning"
          });
          this.appuser.password = "";
          this.passwordConfirm = "";
          return;
        }
        console.log(this.appuser);
        let response = await this.$http.post("/users/register", this.appuser);
        localStorage.setItem("user", response.data.user);
        localStorage.setItem("jwt", response.data.token);
        if (localStorage.getItem("jwt") != null) {
          this.$emit("loggedIn");
          if (this.$route.params.nextUrl != null) {
            this.$router.push(this.$route.params.nextUrl);
          } else {
            this.$router.push("/");
          }
        }
      } catch (e) {
        console.log("error", e);
        this.message = "An error has occurred";
        this.$notify({
          title: "Warning",
          message: "Could not create user.",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style scoped>
</style>