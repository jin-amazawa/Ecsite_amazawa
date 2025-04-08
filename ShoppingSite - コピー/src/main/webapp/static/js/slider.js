
    $(document).ready(function(){
        $('.slider').slick({
            autoplay: true,  // 自動再生
            autoplaySpeed: 3000,  // 3秒ごとに切り替え
            dots: true,  // ナビゲーションのドットを表示
            arrows: true,  // 左右の矢印を表示
            infinite: true,  // 無限ループ
            slidesToShow: 1,  // 表示するスライドの数
            slidesToScroll: 1,  // スクロールするスライドの数
            fade: true,  // フェードアニメーション
            cssEase: 'linear'  // スムーズなスライドアニメーション
        });
    });

