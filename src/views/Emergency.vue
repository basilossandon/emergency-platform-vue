<template>
<div class="accordion">
    <div
        class="accordion-item"
        v-for="emergency, i in emergencies"
        v-bind:class="{ 'accordion-active': emergency.active }"
    >
        <div class="accordion-header">
            <a href="#" v-on:click="expand(event, i)">
                <div class="accordion-header-div">{{ emergency.name }}</div>
                <div class="accordion-header-div">
                    <div class="accordion-caret"></div>
                </div>
            </a>
        </div>
        <div class="accordion-body" v-bind:ref="'accordion-body-' + i">
            <div class="accordion-content">{{ emergency.location }}</div>
        </div>
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
    	expand: function(e, i) {
			e.preventDefault();
			let el = this.$refs['accordion-body-' + i][0];
			if (this.contents[i].active === false) {
				this.contents[i].active = true;

				TweenLite.to(el, 1, {
					height: el.scrollHeight,
					ease: Elastic.easeOut.config(1, 0.3)
				});
			} else {
				this.contents[i].active = false;

				TweenLite.to(el, 0.5, {
					height: 0,
					ease: Bounce.easeOut
				});
			}
    },
  
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
