
<template>
  <div class="container text-center  mt-5 mb-5">
  <h1 class="mt-5 fw-bolder text-success "> Usermanagement Database</h1>
   <div class="table-responsive my-5">

    <!-- The table component -->
     <Table :userData ="userData"></Table> 
   </div>

</div>
</template>
<script>


import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
 import Table from './components/Table.vue'
export default {
  name: 'App',
  components: {
     Table
  },
  data() {
    return {
      userData: []
    };
  },
   
 
  methods: {
     getAnswer() {
     
     axios({
       method: 'get',
       url: 'http://localhost:8080/users/all',
      
     })
       .then(response => {
         for (let i = 0; i < response.data.data.length; i++) {
            this.userData.push(response.data.data[i]);
         }
         console.log('Response:', response.data);
       })
       .catch(error => {
         // Handle error, e.g., display an error message
         console.error('Error:', error.message);
       });

   

    },
  },
  beforeMount() {
    this.getAnswer();
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  
}
body{
    height: 100%;
    background-color: #FFFEFB;
}

</style>
