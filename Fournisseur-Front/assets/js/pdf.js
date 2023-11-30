document.getElementById('downloadpdf').addEventListener('click', function() {
    // Create a new jsPDF instance
    window.jsPDF = window.jspdf.jsPDF;
    const doc = new jsPDF('l', 'mm', 'a4'); // Définir la page en mode paysage
  
    // Get the content of the PHP page
    const content = document.querySelector('#expo');
  
    // Generate the PDF using jsPDF
    doc.html(content, {
      callback: function(doc) {
        doc.save('exported_pdf.pdf');
      },
      x: 25,
      y: 5,
      width: 240, // Ajuster la largeur de la zone de contenu en mode paysage
      windowWidth: 1200
    });  
});