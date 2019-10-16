
<template>
  <div class="container" style="display:flex;justify-content: space-around;">
    <div style="width: 50%; float:left;">
      <img src="../assets/svg/drop.svg" style=";height: 800x;width:100%;" />
    </div>

    <div style="width: 50%; float:right;">
      <div class="form" v-on:click="handleFileUpload()">
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" />
        <h3 style="display:flex; justify-content: center;
align-items: center;"><img class="svg-icon" src="../assets/svg/icon.svg" style=";height: 15%x;width:10%;fill: #909399;" />
   &nbsp; or click in this area.</h3>

      </div>
      <el-button
        type="primary"
        style="margin-top:15px;"
        round
        icon="el-icon-upload"
        v-on:click="submitFile()"
      >Upload</el-button>
    </div>
  </div>
</template>

<script>
export default {
  /*
      Defines the data used by the component
    */
  data() {
    return {
      file: ""
    };
  },

  methods: {
    /*
    onDrop: function(e) {
      e.stopPropagation();
      e.preventDefault();
      var files = e.dataTransfer.files;
      this.createFile(files[0]);
    },
    onChange(e) {
      var files = e.target.files;
      this.createFile(files[0]);
    },
    createFile(file) {
      if (!file.type.match("*.csv")) {
        alert("Upload a CSV type file.");
        return;
      }
      reader.readAsDataURL(file);
    },
    removeFile() {
      this.file = "";
    }, */
    submitFile() {
      /*
                Initialize the form data
            */
      let formData = new FormData();

      /*
                Add the form data we need to submit
            */
      formData.append("file", this.file);

      /*
          Make the request to the POST /single-file URL
        */
      this.$http
        .post("/volunteers/upload", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(function() {
          console.log("SUCCESS!!");
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
    },

    /*
        Handles a change on the file upload
      */
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
};
</script>

<style scoped>
.svg-icon{
  filter: invert(68%) sepia(7%) saturate(255%) hue-rotate(182deg) brightness(86%) contrast(85%);
}
.form {
  width: 100%;
  height: 200px;
  border: 4px dashed #909399;
  border-radius: 25px;
}
.form h3 {
  width: 100%;
  height: 100%;
  text-align: center;
  line-height: 170px;
  color: #909399;
  font-family: Arial;
}
.form input {
  position: absolute;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  outline: none;
  opacity: 0;
}
</style>