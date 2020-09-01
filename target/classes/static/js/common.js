$(document).ready(function(){
	loadPage = () => {
		$('.Backdrop').addClass('Close');
		//$('.Sidebar').addClass('Close');
	}
	loadPage();
	let showSideBar = true;
	//sidebar click
	$('.SidebarToggle').on('click', () => {
		$('.Backdrop').addClass('Open');
		$('.Backdrop').removeClass('Close');
		$('.Sidebar').addClass('Open').removeClass('Close');
	});
	
	$('.Backdrop').on('click', () => {
		$('.Sidebar').addClass('Close').removeClass('Open');
		$('.Backdrop').addClass('Close').removeClass('Open');
	});
	
});