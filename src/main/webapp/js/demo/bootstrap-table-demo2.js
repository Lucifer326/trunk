(function(document, window, $) {
  // Example Bootstrap Table Events
  // ------------------------------
  (function() {
    $('#exampleTableEvents').bootstrapTable({
    // url: "js/demo/bootstrap_table_test.json",
     //search: true,
	    striped: true,  
      sortable: true,      //是否启用排序
	    sortOrder: "ID asc",     //排序方式
      pagination: true,
      //showRefresh: true,
      //showToggle: true,
      //showColumns: true,
      iconSize: 'outline',
      toolbar: '#exampleTableEventsToolbar',
      icons: {
        refresh: 'glyphicon-repeat',
        toggle: 'glyphicon-list-alt',
        columns: 'glyphicon-list'
      }
    });
 })();
})(document, window, jQuery);
