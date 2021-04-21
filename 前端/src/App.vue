<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data: () => ({
      show: true
    }),
    components: {

    },
    created() {
      if(localStorage.eleToken) {
        const token = localStorage.eleToken;
        this.$store.dispatch("setAuthenticated",!this.isEmpty(token));
        this.$store.dispatch("setUser",token);
      }
    },
    methods: {
      isEmpty(value) {
        return (
          value === undefined ||
          value === null ||
          (typeof value === 'object' && Object.keys(value).length === 0) ||
          (typeof value === 'string' && value.trim().length === 0)
        );
      }
    }
  }
</script>

<style>

</style>
