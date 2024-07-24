
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css">
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
  <script>
    $(function() {
      // Initialize datepicker with timepicker addon
      $("#datepicker").datetimepicker({
        dateFormat: 'yy-mm-dd', 
        timeFormat: 'HH:mm',    
        controlType: 'select',   // Use select boxes for hours and minutes
        oneLine: true            // Display date and time in one line
      });
    });
  </script>

  <label for="datepicker">Select a date and time:</label>
  <input type="text" id="datepicker">


