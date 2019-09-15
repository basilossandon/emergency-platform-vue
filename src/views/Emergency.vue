<template>
  <div>
    <h1>All emergencies</h1>
      <div role="tablist" v-for="emergency in emergencies" :key="emergency.id" v-bind:title="{ 'emergencyName': emergency.active }">
      <el-collapse v-model="activeName" accordion>
      <el-collapse-item v-bind:title="emergency.name" name="emergency.id">
        <div>Status: {{emergency.status}}</div>
        <img :src="'https://loremflickr.com/160/120/earthquake?lock='+emergency.id"/>
    </el-collapse-item>
    </el-collapse>
      </div>
    <div v-if="emergencies.length==0" class="empty-list">
      <em>No emergencies found</em>
    </div>
  </div>
</template>

<script>

export default{
  data(){
    return {
      emergencies:[],
      /*page:1,
      perPage: 10,*/
      totalRecords:0,
      pageOptions:{
        texts:{
          count:""
        }
      }
    }
  },
  methods:{
    paginate:function(){
        //this.page = val
        this.getData();
      },

    getData: async function(){
      try{
        /*let offset = this.perPage*(this.page-1);
        let perPage = this.perPage;
        let resource = this.resource;*/
        let response = await this.$http.get(`/emergencies`);
        this.emergencies  = response.data;
        console.log('headers', response.headers)
        this.totalRecords = +response.headers['pagination-count']
      } catch (e) {
        console.log('error', e)
      }
    }
  },
  created:function(){
    this.getData();
  }
}
</script>
