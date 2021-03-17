$(document).ready(function(){
    $(".menu-moblile .button-menu").click(function(){
      $(".menu-moblile ul").animate({
        height: 'toggle'
      },200);
    });
  });