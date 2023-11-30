document.getElementById('downloadpdf').addEventListener('click', function() {
    var element = document.getElementById('.content-wrapper');

    var element = document.getElementById('content-wrapper');
    console.log('W:'+element.scrollWidth);
    //element.style.width = '1200px';
    //element.style.height = '100%';
    //console.log('W:'+element.scrollWidth);

    html2canvas(element, {
        foreignObjectRendering: true,
        
       }).then(function(canvas) {
        // Utiliser canvas.toDataURL() pour obtenir une URL de données de l'image
        var dataUrl = canvas.toDataURL();
        document.body.appendChild(canvas);
        // Créer un nouveau document PDF avec jsPDF
        var doc = new jsPDF();
       
        // Ajouter l'image au document PDF
        doc.addImage(dataUrl, 'JPEG', 0, 0);
       
        // Sauvegarder le document PDF
        var timestamp = new Date().toISOString();
        doc.save('stock' + timestamp + '.pdf');
    });
       
  });