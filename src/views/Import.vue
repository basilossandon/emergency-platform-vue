<template>
  <div class="Import">
    <h1>Import</h1>
    <vue-csv-import
      v-model="csv"
      url="localhost:4567/volunteers/"
      :map-fields="['Column 1', 'Column 2', 'Column 3']"
    >
      <template slot="error">
        File type is invalid
      </template>

      <template slot="thead">
        <tr>
          <th>My Fields</th>
          <th>Column</th>
        </tr>
      </template>

      <template slot="next" slot-scope="{load}">
        <div class="button-emergency-wrapper">
          <el-button
            type="primary"
            round
            icon="el-icon-upload"
            @click.prevent="load"
            >Load File</el-button
          >
        </div>
      </template>

      <template slot="submit" slot-scope="{submit}">
        <div class="button-emergency-wrapper">
          <el-button
            type="primary"
            round
            icon="el-icon-upload"
            @click.prevent="submit"
            >Post</el-button
          >
        </div>
      </template>
    </vue-csv-import>

    <div class="mt-2">
      {{ csv }}
    </div>
    <p>
      Note: You can only import ".csv" files with volunteers data in this
      format: "column1: name", "column2: age", "column3: sex"
    </p>
  </div>
</template>

<script>
  export default {
    name: "app",
    data() {
      return {
        csv: null
      };
    }
  };
</script>

<style>
  #app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  .container {
    text-align: left;
  }

  pre code {
    background-color: #eee;
    border: 1px solid #999;
    display: block;
    padding: 20px;
  }

  #app .form {
    text-align: left;
  }
</style>
