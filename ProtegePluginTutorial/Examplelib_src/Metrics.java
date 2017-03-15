package org.protege.owl.example;

import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.semanticweb.owlapi.model.OWLOntology;

public class Metrics extends JPanel
{
  private static final long serialVersionUID = -2017045836890114258L;
  private JLabel textComponent = new JLabel();
  private JButton refreshButton = new JButton("Refresh");
  private ActionListener refreshAction = new Metrics.1(this);
  private OWLModelManagerListener modelListener = new Metrics.2(this);
  private OWLModelManager modelManager;
  
  public Metrics(OWLModelManager modelManager)
  {
    this.modelManager = modelManager;
    recalculate();
    
    modelManager.addListener(modelListener);
    refreshButton.addActionListener(refreshAction);
    
    add(textComponent);
    add(refreshButton);
  }
  
  public void dispose() {
    refreshButton.removeActionListener(refreshAction);
  }
  
  private void recalculate()
  {
    int count = modelManager.getActiveOntology().getClassesInSignature().size();
    if (count == 0) {
      count = 1;
    }
    textComponent.setText("Total classes = " + count);
  }
}