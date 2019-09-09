<template>
  <div>
    <h1>Todas las emergencias</h1>
    <ul class="item-list">
      <li v-for="emergency in emergencies" :key="emergency.id">
        {{emergency.name}}
      </li>
    </ul>
    <!--<pagination class="paginate" :records="totalRecords" v-model="page" :per-page="perPage" @paginate="paginate" :options="pageOptions"></pagination>-->

    <div v-if="emergencies.length==0" class="empty-list">
      <em>No se han cargado los datos</em>
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
