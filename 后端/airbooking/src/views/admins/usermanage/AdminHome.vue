<template>
  <div>
		<!-- <div class="shownumber">
			<dl>

			</dl>
			<dl>

			</dl>
			<dl>

			</dl>
			<dl>

			</dl>
		</div> -->
		<h1>welcome {{tokenform.searchtext}}</h1>
  </div>
</template>

<script>
export default {
	name: "AdminHome",
	data() {
		return {
			Clist: 0,
			tokenform: {
        searchtext: localStorage.eleToken
      }
		}	
	},
  mounted() {

  },
  methods: {

  }
};
</script>

<style scoped>
 .shownumber {
	 width: 100%;
	 height: 85px;
 }

 .shownumber dl {
	 padding: 0;
	 margin: 0;
	 width: 18%;
	 height: 100%;
	 margin-left: 10px;
	 float: left;
	 background-color: rgb(246, 250, 250);
 }

 .shownumber dl img {
	 width: 50%;
	 height: 100%;
	 padding: 0;
	 margin: 0;
	 float: left;
 }

 .shownumber dl span {
	 font-size: 40px;
	 justify-content: center;
	 margin-top: 12px;
	 display: flex;
 }

</style>
