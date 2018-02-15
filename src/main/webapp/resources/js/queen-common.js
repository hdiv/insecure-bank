$(document).ready(function(){

	/************************
	/*	MAIN NAVIGATION
	/************************/
	
	$mainMenu = $('.main-menu');

	// init collapse first for browser without transition support (IE9) 
	$mainMenu.find('li').has('ul').children('ul').collapse({toggle: false});

	$mainMenu.find('li.active').has('ul').children('ul').addClass('in');
	$mainMenu.find('li').not('.active').has('ul').children('ul').removeClass('in');

	$('.main-menu .submenu-toggle').click( function(e){

		e.preventDefault();

		$currentItem = $(this).parents('li');
		$mainMenu.find('li').not($currentItem).removeClass('active').children('ul.in').collapse('hide');
		$currentItem.toggleClass('active').children('ul').collapse('toggle');
		
	});

	$('.btn-off-canvas').click( function() {
		if($('.wrapper').hasClass('off-canvas-active')) {
			$('.wrapper').removeClass('off-canvas-active');
		} else {
			$('.wrapper').addClass('off-canvas-active');
		}
	});

	$('.btn-nav-sidebar-minified').clickToggle(
		function() {
			$('.wrapper').addClass('main-nav-minified');
			evaluateNavMode();
		},
		function() {
			$('.wrapper').removeClass('main-nav-minified');
			evaluateNavMode();
		}
	);

	$(window).resize(evaluateNavMode);

	evaluateNavMode();

	function evaluateNavMode() {
		/* if window width larger than 1200px, apply slimScroll on nav (15 for scrollbar width)
		 * also destory off-canvas if previously activated 
		 */
		if( ($(window).width()+15) >= 1200 && $('.wrapper.main-nav-minified').length == 0 ) {
			applySlimScroll();
			$('.wrapper').removeClass('off-canvas-active');
		} else {
			destroySlimScroll('#main-nav');
		}
	}

	function applySlimScroll() {
		$('#main-nav').slimScroll({
			width: '100%',
			height: '550px',
			size: '5px',
			wheelStep: 5,
			railVisible: true,
			railColor: '#fff'
		});
	}

	function destroySlimScroll(objectId) {
		$(objectId).parents('.slimScrollDiv').replaceWith($(objectId)); 
	}
	

	/************************
	/*	SKIN SWITCHER
	/************************/

	// check if skin has already applied before
	var skin = localStorage.getItem('queenSkin');
	var skinLogo = localStorage.getItem('queenSkinLogo');
	var skinLogoDefault = 'assets/img/queenadmin-logo.png';

	if(skin != null) {
		$('head').append('<link rel="stylesheet" href="' + skin + '" type="text/css" />');
	}

	if(skinLogo != null) {
		$('.logo img').attr('src', skinLogo);
	}

	// skin button action
	$('.btn-skin').click( function(e) {

		e.preventDefault();

		resetStyle();
		$('head').append('<link rel="stylesheet" href="' + $(this).attr('data-skin') + '" type="text/css" />');

		if(!$(this).hasClass('full-white')) {
			skinLogo = 'assets/img/queenadmin-logo-white.png';
		}else {
			skinLogo = skinLogoDefault;
		}

		$('.logo img').attr('src', skinLogo);

		localStorage.setItem('queenSkin', $(this).attr('data-skin'));
		localStorage.setItem('queenSkinLogo', skinLogo);
	});

	// reset stlye
	$('.reset-style').click( function() {
		resetStyle();
	});

	function resetStyle() {
		$('head link[rel="stylesheet"]').each( function() {

			if( $(this).attr('href').toLowerCase().indexOf("skins") >= 0 )
				$(this).remove();
		});

		$('.logo img').attr('src', 'assets/img/queenadmin-logo.png');

		localStorage.removeItem('queenSkin');
		localStorage.setItem('queenSkinLogo', skinLogoDefault);
	}


	/************************
	/*	SIDEBAR
	/************************/

	$('.toggle-right-sidebar').click( function(e) {
		$(this).toggleClass('active');
		$('.right-sidebar').toggleClass('active');
	});
	

	/************************
	/*	WIDGET
	/************************/

	// widget remove
	$('.widget .btn-remove').click( function(e) {

		e.preventDefault();
		$(this).parents('.widget').fadeOut(300, function() {
			$(this).remove();
		});
	});

	// widget toggle expand
	$('.widget .btn-toggle-expand').clickToggle(
		function(e) {
			e.preventDefault();
			$(this).parents('.widget').find('.slimScrollDiv').css('height', 'auto');
			$(this).parents('.widget').find('.widget-content').slideUp(300);
			$(this).find('i').removeClass('ion-ios7-arrow-up').addClass('ion-ios7-arrow-down');
		},
		function(e) {
			e.preventDefault();
			$(this).parents('.widget').find('.widget-content').slideDown(300);
			$(this).find('i').removeClass('ion-ios7-arrow-down').addClass('ion-ios7-arrow-up');
		}
	);
	

	/************************
	/*	BOOTSTRAP TOOLTIP
	/************************/

	$('body').tooltip({
		selector: "[data-toggle=tooltip]",
		container: "body"
	});


	/************************
	/*	BOOTSTRAP POPOVER
	/************************/

	$('.demo-popover1 #popover-title').popover({
		html: true,
		title: '<i class="icon ion-ios7-chatbubble"></i> Popover Title',
		content: 'This popover has title and support HTML content. Quickly implement process-centric networks rather than compelling potentialities. Objectively reinvent competitive technologies after high standards in process improvements. Phosfluorescently cultivate 24/365.'
	});

	$('.demo-popover1 #popover-hover').popover({
		html: true,
		title: '<i class="icon ion-ios7-chatbubble"></i> Popover Title',
		trigger: 'hover',
		content: 'Activate the popover on hover. Objectively enable optimal opportunities without market positioning expertise. Assertively optimize multidisciplinary benefits rather than holistic experiences. Credibly underwhelm real-time paradigms with.'
	});

	$('.demo-popover2 .btn').popover();


	/************************
	/*	TODO LIST
	/************************/

	if( $('.todo-list').length > 0 ) {
		$('.todo-list').sortable({
			revert: true,
			placeholder: "ui-state-highlight",
			handle: '.handle'
		});

		$('.todo-list input').change( function() {
			if( $(this).prop('checked') ) {
				$(this).parents('li').addClass('completed');
			}else {
				$(this).parents('li').removeClass('completed');
			}
		});
	}


	//*******************************************
	/*	DROPZONE FILE UPLOAD
	/********************************************/

	// if dropzone exist
	if( $('.dropzone').length > 0 ) {
		Dropzone.autoDiscover = false;
		
		$(".dropzone").dropzone({
			url: "php/dropzone-upload.php",
			addRemoveLinks : true,
			maxFilesize: 0.5,
			maxFiles: 5,
			acceptedFiles: 'image/*, application/pdf, .txt',
			dictResponseError: 'File Upload Error.'
		});
	} // end if dropzone exist


	//*******************************************
	/*	WIDGET SLIM SCROLL
	/********************************************/

	if( $('body.dashboard').length > 0) {
		$('.widget-todo .widget-content').slimScroll({
			height: '400px',
			wheelStep: 5,
		});

		$('.widget-live-feed .widget-content').slimScroll({
			height: '409px',
			wheelStep: 5,
		});
	}

	$('.widget-chat-contacts .widget-content').slimScroll({
		height: '800px',
		wheelStep: 5,
		railVisible: true,
		railColor: '#fff'
	});
	



	//*******************************************
	/*	SELECT2
	/********************************************/

	if( $('.select2').length > 0) {
		$('.select2').select2();
	}

	if( $('.select2-multiple').length > 0) {
		$('.select2-multiple').select2();
	}


	//*******************************************
	/*	WIDGET SCRIPTS
	/********************************************/

	$('.widget-single-multiselect').multiselect({
		buttonClass: 'btn btn-success btn-xs',
		templates: {
			li: '<li><a href="javascript:void(0);"><label><i></i></label></a></li>'
		}
	});

	$('.btn-help').popover({
		container: 'body',
		placement: 'top',
		html: true,
		title: '<i class="icon ion-help-circled"></i> Help',
		content: "Help summary goes here. Options can be passed via data attributes <code>data-</code> or JavaScript. Please check <a href='http://getbootstrap.com/javascript/#popovers'>Bootstrap Doc</a>"
	});
	
});

	// toggle function
	$.fn.clickToggle = function( f1, f2 ) {
		return this.each( function() {
			var clicked = false;
			$(this).bind('click', function() {
				if(clicked) {
					clicked = false;
					return f2.apply(this, arguments);
				}

				clicked = true;
				return f1.apply(this, arguments);
			});
		});

	}