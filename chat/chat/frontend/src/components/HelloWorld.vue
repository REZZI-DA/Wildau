<template>
  <div>
    <h1>Scraped test3</h1>
    <ul>
      <li v-for="(item, index) in scrapedData" :key="index">{{ index + 1 }}. {{ item }}</li>
    </ul>
  </div>
</template>

<script>

import axios from 'axios';
export default {
  data() {
    return {
      scrapedData: [],
    };

  },
  scrapedData() {
    return "";
  },
  mounted() {
    // Web-Scraping-Aufgabe
    // const store = useStore();
    // store.dispatch('fetchData');
    // // Aktion im Vuex-Store aufrufen
    const postData = {
      username: 'john_doe',
      email: 'john@example.com',
      password: 'secretpassword',
      phoneNumber: 'secretpassword',
    };
    
    axios({
      method: 'post',
      url: '/users/',
      data: postData,
    })
      .then(response => {
        // Handle success, e.g., display a success message
        console.log('Response:', response.data);
      })
      .catch(error => {
        // Handle error, e.g., display an error message
        console.error('Error:', error.message);
      });

  },


};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}</style>
