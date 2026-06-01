import { useEffect } from 'react';
import './App.css';

function App() {
  useEffect(() => {
    // === 1. AOS (Animate On Scroll) の初期化 ===
    // @ts-ignore
    if (window.AOS) {
      // @ts-ignore
      window.AOS.init({
        duration: 1000,
        easing: 'ease-in-out',
        once: true,
        mirror: false
      });
    }

    // === 2. GLightboxの初期化 ===
    // @ts-ignore
    if (window.GLightbox) {
      // @ts-ignore
      window.GLightbox({ selector: '.glightbox' });
    }

    // === 🌟【追加】3. Swiper (ロゴスライダー) の初期化 🌟 ===
    // @ts-ignore
    if (window.Swiper) {
      document.querySelectorAll('.init-swiper').forEach((swiperElement) => {
        const configElement = swiperElement.querySelector('.swiper-config');
        if (configElement) {
          try {
            const config = JSON.parse(configElement.innerHTML);
            // @ts-ignore
            new window.Swiper(swiperElement, config);
          } catch (e) {
            console.error('SwiperのJSONパースに失敗しました:', e);
          }
        }
      });
    }

    // === 4. プリローダーの削除 ===
    const preloader = document.querySelector('#preloader');
    if (preloader) {
      setTimeout(() => {
        preloader.remove();
      }, 350);
    }

    window.dispatchEvent(new Event('load'));
  }, []);

  return (
    <>
      {/* ======= Header ======= */}
      <header id="header" className="header d-flex align-items-center fixed-top">
        <div className="container-fluid container-xl position-relative d-flex align-items-center">
          <a href="/" className="logo d-flex align-items-center me-auto">
            <h1 className="sitename">GasWorks</h1>
          </a>

          <nav id="navmenu" className="navmenu">
            <ul>
              <li><a href="#hero" className="active">ホーム</a></li>
              <li><a href="#about">私たちについて</a></li>
              <li><a href="#services">サービス</a></li>
              <li><a href="#portfolio">実績</a></li>
              <li><a href="#team">チーム</a></li>
              <li><a href="#pricing">プラン</a></li>
              <li className="dropdown"><a href="#"><span>メニュー</span> <i className="bi bi-chevron-down toggle-dropdown"></i></a>
                <ul>
                  <li><a href="#">進行中の案件</a></li>
                  <li className="dropdown"><a href="#"><span>報告書作成</span> <i className="bi bi-chevron-down toggle-dropdown"></i></a>
                    <ul>
                      <li><a href="#">点検報告書</a></li>
                      <li><a href="#">施工完了報告</a></li>
                      <li><a href="#">緊急対応記録</a></li>
                    </ul>
                  </li>
                  <li><a href="#">スケジュール</a></li>
                  <li><a href="#">資材管理</a></li>
                </ul>
              </li>
              <li><a href="#contact">お問い合わせ</a></li>
            </ul>
            <i className="mobile-nav-toggle d-xl-none bi bi-list"></i>
          </nav>

          <a className="btn-getstarted" href="#about">ログイン</a>
        </div>
      </header>

      <main className="main">

        {/* Hero Section */}
        <section id="hero" className="hero section dark-background">
          <div className="container">
            <div className="row gy-4">
              <div className="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center" data-aos="zoom-out">
                <h1>安心・安全なガスライフを、確かな技術で支える。</h1>
                <p>GasWorksは、現場と管理をデジタルで繋ぎ、ガス設備工事の安全性と効率を最大化する次世代ソリューションです。</p>
                <div className="d-flex">
                  <a href="#about" className="btn-get-started">詳しく見る</a>
                  <a href="https://www.youtube.com/watch?v=Y7f98aduVJ8" className="glightbox btn-watch-video d-flex align-items-center"><i className="bi bi-play-circle"></i><span>紹介動画</span></a>
                </div>
              </div>
              <div className="col-lg-6 order-1 order-lg-2 hero-img" data-aos="zoom-out" data-aos-delay="200">
                <img src="/assets/img/hero-img.png" className="img-fluid animated" alt="" />
              </div>
            </div>
          </div>
        </section>

        {/* Clients Section */}
        <section id="clients" className="clients section light-background">
          <div className="container" data-aos="zoom-in">
            <div className="swiper init-swiper">
              <script type="application/json" className="swiper-config" dangerouslySetInnerHTML={{ __html: `
                {
                  "loop": true,
                  "speed": 600,
                  "autoplay": { "delay": 5000 },
                  "slidesPerView": "auto",
                  "breakpoints": {
                    "320": { "slidesPerView": 2, "spaceBetween": 40 },
                    "480": { "slidesPerView": 3, "spaceBetween": 60 },
                    "640": { "slidesPerView": 4, "spaceBetween": 80 },
                    "992": { "slidesPerView": 5, "spaceBetween": 120 },
                    "1200": { "slidesPerView": 6, "spaceBetween": 120 }
                  }
                }
              `}} />
              <div className="swiper-wrapper align-items-center">
                <div className="swiper-slide"><img src="/assets/img/clients/clients-1.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-2.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-3.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-4.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-5.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-6.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-7.webp" className="img-fluid" alt="" /></div>
                <div className="swiper-slide"><img src="/assets/img/clients/clients-8.webp" className="img-fluid" alt="" /></div>
              </div>
            </div>
          </div>
        </section>

        {/* About Section */}
        <section id="about" className="about section">
          <div className="container section-title" data-aos="fade-up">
            <h2>私たちについて</h2>
          </div>
          <div className="container">
            <div className="row gy-4">
              <div className="col-lg-6 content" data-aos="fade-up" data-aos-delay="100">
                <p>GasWorksは、地域のライフラインであるガス設備を安全に守り続けるために開発されました。</p>
                <ul>
                  <li><i className="bi bi-check2-circle"></i> <span>現場作業員と管理者のリアルタイムな情報共有</span></li>
                  <li><i className="bi bi-check2-circle"></i> <span>写真やチェックリストによる徹底した品質・安全管理</span></li>
                  <li><i className="bi bi-check2-circle"></i> <span>過去の点検データに基づいた最適なメンテナンス提案</span></li>
                </ul>
              </div>
              <div className="col-lg-6" data-aos="fade-up" data-aos-delay="200">
                <p>複雑な工事工程や法的点検をデジタル化することで、人為的ミスを防ぎ、より確実な施工を実現します。最新のテクノロジーを活用し、安心の環境づくりに貢献します。</p>
                <a href="#" className="read-more"><span>詳細を見る</span><i className="bi bi-arrow-right"></i></a>
              </div>
            </div>
          </div>
        </section>

        {/* Why Us Section */}
        <section id="why-us" className="section why-us light-background">
          <div className="container-fluid">
            <div className="row gy-4">
              <div className="col-lg-7 d-flex flex-column justify-content-center order-2 order-lg-1">
                <div className="content px-xl-5" data-aos="fade-up" data-aos-delay="100">
                  <h3>GasWorksが選ばれる <strong>3つの理由</strong></h3>
                  <p>現場を知り尽くした設計により、日々の業務に溶け込む使いやすさを提供します。</p>
                </div>
                <div className="faq-container px-xl-5" data-aos="fade-up" data-aos-delay="200">
                  <div className="faq-item faq-active">
                    <h3><span>01</span> 確かな技術力と安全性</h3>
                    <div className="faq-content"><p>国家資格を保持した熟練のエンジニアによる施工と、厳格なデジタルチェックを組み合わせています。</p></div>
                    <i className="faq-toggle bi bi-chevron-right"></i>
                  </div>
                  <div className="faq-item">
                    <h3><span>02</span> 24時間365日の監視体制</h3>
                    <div className="faq-content"><p>異常を検知した際は、即座に担当者へ通知し、迅速な一次対応を可能にします。</p></div>
                    <i className="faq-toggle bi bi-chevron-right"></i>
                  </div>
                  <div className="faq-item">
                    <h3><span>03</span> クラウドによる徹底した写真管理</h3>
                    <div className="faq-content"><p>すべての施工工程を写真で記録。隠蔽部の配管も後から確認できるため、高い透明性を確保しています。</p></div>
                    <i className="faq-toggle bi bi-chevron-right"></i>
                  </div>
                </div>
              </div>
              <div className="col-lg-5 order-1 order-lg-2 why-us-img">
                <img src="/assets/img/why-us.png" className="img-fluid" alt="" data-aos="zoom-in" data-aos-delay="100" />
              </div>
            </div>
          </div>
        </section>

        {/* Skills Section (Work Process) */}
        <section id="skills" className="skills section">
          <div className="container" data-aos="fade-up" data-aos-delay="100">
            <div className="row">
              <div className="col-lg-6 d-flex align-items-center">
                <img src="/assets/img/skills.png" className="img-fluid" alt="" />
              </div>
              <div className="col-lg-6 pt-4 pt-lg-0 content">
                <h3>施工・保守のデジタル進捗率</h3>
                <p className="fst-italic">現場でのデジタル活用を推進し、ミスのない確実な施工を行っています。</p>
                <div className="skills-content ps-xl-4">
                  <div className="progress">
                    <span className="skill"><span>点検のデジタル記録率</span> <i className="val">100%</i></span>
                    <div className="progress-bar-wrap"><div className="progress-bar" role="progressbar" aria-valuenow={100} aria-valuemin={0} aria-valuemax={100} style={{width: '100%'}}></div></div>
                  </div>
                  <div className="progress">
                    <span className="skill"><span>資材管理の自動化率</span> <i className="val">90%</i></span>
                    <div className="progress-bar-wrap"><div className="progress-bar" role="progressbar" aria-valuenow={90} aria-valuemin={0} aria-valuemax={100} style={{width: '90%'}}></div></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Services Section */}
        <section id="services" className="services section light-background">
          <div className="container section-title" data-aos="fade-up">
            <h2>サービス内容</h2>
            <p>ガス設備の点検から新規施工、緊急修理までトータルにサポートします。</p>
          </div>
          <div className="container">
            <div className="row gy-4">
              <div className="col-xl-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="100">
                <div className="service-item position-relative">
                  <div className="icon"><i className="bi bi-activity icon"></i></div>
                  <h4><a href="" className="stretched-link">定期点検</a></h4>
                  <p>法的要件に基づいた確実な点検を行い、異常の早期発見に努めます。</p>
                </div>
              </div>
              <div className="col-xl-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="200">
                <div className="service-item position-relative">
                  <div className="icon"><i className="bi bi-bounding-box-circles icon"></i></div>
                  <h4><a href="" className="stretched-link">新規設置工事</a></h4>
                  <p>最新の省エネ機器への交換や、新築住宅の配管工事を承ります。</p>
                </div>
              </div>
              <div className="col-xl-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="300">
                <div className="service-item position-relative">
                  <div className="icon"><i className="bi bi-calendar4-week icon"></i></div>
                  <h4><a href="" className="stretched-link">緊急修理</a></h4>
                  <p>ガス漏れや故障などのトラブルに、専門のエンジニアが迅速に駆けつけます。</p>
                </div>
              </div>
              <div className="col-xl-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="400">
                <div className="service-item position-relative">
                  <div className="icon"><i className="bi bi-broadcast icon"></i></div>
                  <h4><a href="" className="stretched-link">デジタル管理</a></h4>
                  <p>施工データの可視化や、管理の自動化をサポートするシステムを提供します。</p>
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Call To Action Section */}
        <section id="call-to-action" className="call-to-action section dark-background">
          <img src="/assets/img/cta-bg.jpg" alt="" />
          <div className="container">
            <div className="row" data-aos="zoom-in" data-aos-delay="100">
              <div className="col-xl-9 text-center text-xl-start">
                <h3>緊急のお困りごとはございませんか？</h3>
                <p>ガスの不具合や異常を感じた際は、すぐにご連絡ください。専門スタッフが即座に対応いたします。</p>
              </div>
              <div className="col-xl-3 cta-btn-container text-center">
                <a className="cta-btn align-middle" href="#contact">お問い合わせ</a>
              </div>
            </div>
          </div>
        </section>

        {/* Portfolio Section */}
        <section id="portfolio" className="portfolio section">
          <div className="container section-title" data-aos="fade-up">
            <h2>施工実績</h2>
            <p>一般住宅から大規模施設まで、これまで手掛けてきた主な工事事例をご紹介します。</p>
          </div>
          <div className="container">
            <div className="isotope-layout" data-default-filter="*" data-layout="masonry" data-sort="original-order">
              <ul className="portfolio-filters isotope-filters" data-aos="fade-up" data-aos-delay="100">
                <li data-filter="*" className="filter-active">すべて</li>
                <li data-filter=".filter-app">戸建て</li>
                <li data-filter=".filter-product">集合住宅</li>
                <li data-filter=".filter-branding">店舗・施設</li>
              </ul>
              <div className="row gy-4 isotope-container" data-aos="fade-up" data-aos-delay="200">
                <div className="col-lg-4 col-md-6 portfolio-item isotope-item filter-app">
                  <img src="/assets/img/portfolio/portfolio-1.jpg" className="img-fluid" alt="" />
                  <div className="portfolio-info">
                    <h4>戸建て配管更新</h4>
                    <p>築30年の住宅にて配管を刷新。</p>
                    <a href="/assets/img/portfolio/portfolio-1.jpg" title="戸建て配管更新" data-gallery="portfolio-gallery-app" className="glightbox preview-link"><i className="bi bi-zoom-in"></i></a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 portfolio-item isotope-item filter-product">
                  <img src="/assets/img/portfolio/portfolio-2.jpg" className="img-fluid" alt="" />
                  <div className="portfolio-info">
                    <h4>給湯器交換</h4>
                    <p>マンションにてエコジョーズ設置。</p>
                    <a href="/assets/img/portfolio/portfolio-2.jpg" title="給湯器交換" data-gallery="portfolio-gallery-product" className="glightbox preview-link"><i className="bi bi-zoom-in"></i></a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 portfolio-item isotope-item filter-branding">
                  <img src="/assets/img/portfolio/portfolio-3.jpg" className="img-fluid" alt="" />
                  <div className="portfolio-info">
                    <h4>店舗・施設</h4>
                    <p>商業施設の厨房ガス設備施工。</p>
                    <a href="/assets/img/portfolio/portfolio-3.jpg" title="商業施設配管" data-gallery="portfolio-gallery-branding" className="glightbox preview-link"><i className="bi bi-zoom-in"></i></a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 portfolio-item isotope-item filter-app">
                  <img src="/assets/img/portfolio/portfolio-4.jpg" className="img-fluid" alt="" />
                  <div className="portfolio-info">
                    <h4>アパートガス供給設備</h4>
                    <p>集合住宅の集中プロパン監視システム導入。</p>
                    <a href="/assets/img/portfolio/portfolio-4.jpg" title="アパートガス供給設備" data-gallery="portfolio-gallery-app" className="glightbox preview-link"><i className="bi bi-zoom-in"></i></a>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 portfolio-item isotope-item filter-product">
                  <img src="/assets/img/portfolio/portfolio-5.jpg" className="img-fluid" alt="" />
                  <div className="portfolio-info">
                    <h4>ハイブリッド給湯器</h4>
                    <p>最新の電気・ガスハイブリッド給湯システム設置。</p>
                    <a href="/assets/img/portfolio/portfolio-5.jpg" title="ハイブリッド給湯器" data-gallery="portfolio-gallery-product" className="glightbox preview-link"><i className="bi bi-zoom-in"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Team Section */}
        <section id="team" className="team section">
          <div className="container section-title" data-aos="fade-up">
            <h2>チーム</h2>
            <p>GasWorksを支える熟練のスタッフです。</p>
          </div>
          <div className="container">
            <div className="row gy-4">
              <div className="col-lg-6" data-aos="fade-up" data-aos-delay="100">
                <div className="team-member d-flex align-items-start">
                  <div className="pic"><img src="/assets/img/team/team-1.jpg" className="img-fluid" alt="" /></div>
                  <div className="member-info">
                    <h4>佐藤 健一</h4>
                    <span>施工責任者</span>
                    <p>経験豊富な現場リーダーです。</p>
                  </div>
                </div>
              </div>
              <div className="col-lg-6" data-aos="fade-up" data-aos-delay="200">
                <div className="team-member d-flex align-items-start">
                  <div className="pic"><img src="/assets/img/team/team-2.jpg" className="img-fluid" alt="" /></div>
                  <div className="member-info">
                    <h4>田中 舞</h4>
                    <span>保守点検リーダー</span>
                    <p>安全管理のスペシャリストです。</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Pricing Section */}
        <section id="pricing" className="pricing section light-background">
          <div className="container section-title" data-aos="fade-up">
            <h2>プラン</h2>
            <p>ご要望に合わせた保守プランをご用意しています。</p>
          </div>
          <div className="container">
            <div className="row gy-4">
              <div className="col-lg-4" data-aos="zoom-in" data-aos-delay="100">
                <div className="pricing-item">
                  <h3>基本プラン</h3>
                  <h4><sup>￥</sup>5,500<span> / 年</span></h4>
                  <ul>
                    <li><i className="bi bi-check"></i> 年1回の定期点検</li>
                    <li><i className="bi bi-check"></i> デジタル報告書</li>
                    <li className="na"><i className="bi bi-x"></i> <span>緊急駆けつけ</span></li>
                  </ul>
                  <a href="#" className="buy-btn">申し込む</a>
                </div>
              </div>
              <div className="col-lg-4" data-aos="zoom-in" data-aos-delay="200">
                <div className="pricing-item featured">
                  <h3>安心フルサポート</h3>
                  <h4><sup>￥</sup>12,000<span> / 年</span></h4>
                  <ul>
                    <li><i className="bi bi-check"></i> 年2回の定期点検</li>
                    <li><i className="bi bi-check"></i> 24時間緊急駆けつけ</li>
                    <li><i className="bi bi-check"></i> 優先修理対応</li>
                  </ul>
                  <a href="#" className="buy-btn">申し込む</a>
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Contact Section */}
        <section id="contact" className="contact section">
          <div className="container section-title" data-aos="fade-up">
            <h2>お問い合わせ</h2>
            <p>工事のご相談やお問い合わせはこちらから承ります。</p>
          </div>
          <div className="container" data-aos="fade-up" data-aos-delay="100">
            <div className="row gy-4">
              <div className="col-lg-5">
                <div className="info-wrap">
                  <div className="info-item d-flex" data-aos="fade-up" data-aos-delay="200">
                    <i className="bi bi-geo-alt flex-shrink-0"></i>
                    <div>
                      <h3>所在地</h3>
                      <p>東京都〇〇区△△ 1-2-3</p>
                    </div>
                  </div>
                  <div className="info-item d-flex" data-aos="fade-up" data-aos-delay="300">
                    <i className="bi bi-telephone flex-shrink-0"></i>
                    <div>
                      <h3>お電話</h3>
                      <p>03-1234-5678</p>
                    </div>
                  </div>
                  <div className="info-item d-flex" data-aos="fade-up" data-aos-delay="400">
                    <i className="bi bi-envelope flex-shrink-0"></i>
                    <div>
                      <h3>メール</h3>
                      <p>support@gasworks.example.com</p>
                    </div>
                  </div>
                  <iframe 
                    src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d48389.78314118045!2d-74.006138!3d40.710059!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c25a22a3bda30d%3A0xb89d1fe6bc499443!2sDowntown%20Conference%20Center!5e0!3m2!1sen!2sus!4v1676961268712!5m2!1sen!2sus" 
                    style={{ border: 0, width: '100%', height: '270px' }} 
                    allowFullScreen 
                    loading="lazy">
                  </iframe>
                </div>
              </div>
              <div className="col-lg-7">
                <form action="#" method="post" className="php-email-form">
                  <div className="row gy-4">
                    <div className="col-md-6">
                      <label htmlFor="name-field" className="pb-2">お名前</label>
                      <input type="text" name="name" id="name-field" className="form-control" required />
                    </div>
                    <div className="col-md-6">
                      <label htmlFor="email-field" className="pb-2">メールアドレス</label>
                      <input type="email" className="form-control" name="email" id="email-field" required />
                    </div>
                    <div className="col-md-12">
                      <label htmlFor="subject-field" className="pb-2">件名</label>
                      <input type="text" className="form-control" name="subject" id="subject-field" required />
                    </div>
                    <div className="col-md-12">
                      <label htmlFor="message-field" className="pb-2">内容</label>
                      <textarea className="form-control" name="message" rows={10} id="message-field" required></textarea>
                    </div>
                    <div className="col-md-12 text-center">
                      <div className="loading">送信中...</div>
                      <div className="error-message"></div>
                      <div className="sent-message">ありがとうございます。送信されました。</div>
                      <button type="submit">送信する</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>

      </main>

      <footer id="footer" className="footer">
        <div className="container footer-top">
          <div className="row gy-4">
            <div className="col-lg-4 col-md-6 footer-about">
              <a href="/" className="d-flex align-items-center"><span className="sitename">GasWorks</span></a>
              <div className="footer-contact pt-3">
                <p>東京都〇〇区△△ 1-2-3</p>
                <p className="mt-3"><strong>電話:</strong> <span>03-1234-5678</span></p>
              </div>
            </div>
          </div>
        </div>

        <div className="container copyright text-center mt-4">
          <p>© <span>Copyright</span> <strong className="px-1 sitename">GasWorks</strong> <span>All Rights Reserved</span></p>
        </div>
      </footer>

      <a href="#" id="scroll-top" className="scroll-top d-flex align-items-center justify-content-center"><i className="bi bi-arrow-up-short"></i></a>
      <div id="preloader"></div>
    </>
  );
}

export default App;