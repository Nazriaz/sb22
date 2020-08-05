Vue.component('messages-list', {
    template: '<div>List</div>'
});
var app = new Vue({
    el: '#app',
    template:'<messages-list />',
    data: {
        messages: [
            {id: '123', text: 'WoW'},
            {id: '23', text: 'More'},
            {id: '3', text: 'Another'}
        ]
    }
});