<template>
  <div>
    <h1>welcome {{tokenform.searchtext}}</h1>
  </div>
</template>

<script>
export default {
  name: "Ahome",
  data() {
    return {
      tokenform: {
        searchtext: localStorage.eleToken
      }
    };
  }
};
</script>

<style scoped>
</style>
