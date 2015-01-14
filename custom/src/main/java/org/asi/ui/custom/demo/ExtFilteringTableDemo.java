/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.custom.demo;

import org.asi.ui.custom.demo.util.Utils;
import org.asi.ui.custom.demo.dto.MyTableDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.RowHeaderMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezeFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedFilterTable;
import org.asi.ui.extfilteringtable.freezetable.listener.TableCollapseListener;
import org.asi.ui.extfilteringtable.freezetable.listener.TableExpandListener;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author Abhiram
 */
public class ExtFilteringTableDemo extends VerticalLayout implements View{
    public static final String NAME="extfilteringtable-demo";
    
    private final BeanItemContainer<MyTableDTO> phasedProjectionBean = new BeanItemContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean2 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean1 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    
    private final Map<Object, Object[]> dMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> dMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    public ExtFilteringTableDemo(){
        init();
    }
    
    private void init() {
        setMargin(true);
        setSpacing(true);
        loadData();
        getMapVisibleCols();
        loadTables();
        
    }
    private void loadData() {
        for (int i = 0; i < 48; i++) {
            MyTableDTO ob = new MyTableDTO();
            MyTableDTO om = new MyTableDTO();
            om.setProjectionType(i);
            om.setProjCol1Sales("" + i);
            phasedProjectionBean.addBean(om);

            if (i == 1) {
                ob.setProjectionType(500);
                ob.setProjCol1Sales("I m parent");
                availableProductsBean.addBean(ob);
                
                availableProductsBean.setChildrenAllowed(ob, true);
                MyTableDTO oc = new MyTableDTO();
                oc.setProjCol1Sales("I m 1st child");
                oc.setProjectionType(600);
                availableProductsBean.addBean(oc);
                availableProductsBean.setParent(oc, ob);
                MyTableDTO oe = new MyTableDTO();
                oe.setProjCol1Sales("I m 2nd child");
                oe.setProjectionType(700);

                availableProductsBean.addBean(oe);
                availableProductsBean.setParent(oe, ob);
                availableProductsBean.setChildrenAllowed(oe, false);
                availableProductsBean.setChildrenAllowed(oc, true);
                MyTableDTO od = new MyTableDTO();
                od.setProjCol1Sales("I m child of 1st child");

                availableProductsBean.addBean(od);
                availableProductsBean.setParent(od, oc);
            } else {
                ob.setProjectionType(i);
                availableProductsBean.addBean(ob);
                availableProductsBean.setChildrenAllowed(ob, false);
            }
        }

    }

    private void getMapVisibleCols() {
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[0], new Object[]{Utils.viscolumnless[0]});
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[1], new Object[]{Utils.viscolumnless[1], Utils.viscolumnless[2]});
        
        tMapVisibleColumnsLess.put(Utils.tviscolumnless[0], new Object[]{Utils.dviscolumnless[0], Utils.dviscolumnless[1]});
        
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[0], new Object[]{Utils.viscolumnmore[0],Utils.viscolumnmore[1]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[1], new Object[]{Utils.viscolumnmore[2]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[2], new Object[]{Utils.viscolumnmore[3],Utils.viscolumnmore[4],Utils.viscolumnmore[5]});
        int j = 6;
        for (int i = 3; i < Utils.dviscolumnmore.length; i++) {
            dMapVisibleColumnsMore.put(Utils.dviscolumnmore[i], new Object[]{Utils.viscolumnmore[j], Utils.viscolumnmore[j + 1], Utils.viscolumnmore[j + 2], Utils.viscolumnmore[j + 3], Utils.viscolumnmore[j + 4], Utils.viscolumnmore[j + 5]});
            j = j + 6;
        }
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[0], new Object[]{Utils.dviscolumnmore[0], Utils.dviscolumnmore[1]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[1], new Object[]{Utils.dviscolumnmore[2]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[2], new Object[]{Utils.dviscolumnmore[3],Utils.dviscolumnmore[4],Utils.dviscolumnmore[5]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[3], new Object[]{Utils.dviscolumnmore[6], Utils.dviscolumnmore[7], Utils.dviscolumnmore[8],Utils.dviscolumnmore[9]});
    }
    private void loadTables() {
        TabSheet tabsheet=new TabSheet();
        addComponent(tabsheet);
        
        tabsheet.addTab(loadExtFilterTable(), "Ext Filter Table");
        tabsheet.addTab(loadFreezeFilterTreeTable(), "Freeze Filter Tree Table");
        tabsheet.addTab(loadPagedFilterTable(), "Paged Filter Table");
        tabsheet.addTab(loadFreezePagedFilterTable(), "Freeze Paged Filter Table");
    }
    private VerticalLayout loadExtFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTable extFilterTable = new ExtFilterTable();
        extFilterTable.setSizeFull();
        extFilterTable.setCaption("ExtFilterTable");
        extFilterTable.setContainerDataSource(phasedProjectionBean);
        extFilterTable.setVisibleColumns(Utils.viscolumnmore);
        extFilterTable.setColumnHeaders(Utils.visheadermore);
        extFilterTable.setFilterBarVisible(true);
        extFilterTable.setDoubleHeaderVisible(true);
        
        extFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        
        extFilterTable.setColumnCollapsingAllowed(true);
        extFilterTable.setTripleHeaderVisible(true);

        extFilterTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
        extFilterTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
        extFilterTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extFilterTable.setSelectable(true);
        extFilterTable.setPageLength(10);
        extFilterTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        extFilterTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
        extFilterTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
        extFilterTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");
        
        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);
        
        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extFilterTable.setImmediate(true);
        extFilterTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {

            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                Notification.show("Triple Check PrropertyId: " + event.getPropertyId()+" Pre: " + event.isChecked());
            }
        });
        extFilterTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {

            @Override
           public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
               if(event.getPropertyId().equals(Utils.tviscolumnmore[0])){
                   extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
               }
            }
        });
        extFilterTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName()+" Cur: " + event.getCurrentValue()+" Pre: " + event.getPreviousValue());
            }
        });
        extFilterTable.addTripleHeaderColumnResizeListener(new ExtCustomTable.TripleHeaderColumnResizeListener() {

            @Override
            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
                 Notification.show("Triple PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {

            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }

            
        });
        extFilterTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {

            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                 Notification.show("PrropertyId: " + event.getPropertyId()+" Pre: " + event.getPreviousWidth()+" Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addTripleHeaderClickListener(new ExtCustomTable.TripleHeaderClickListener() {

            @Override
            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addDoubleHeaderClickListener(new ExtCustomTable.DoubleHeaderClickListener() {

            @Override
            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addHeaderClickListener(new ExtCustomTable.HeaderClickListener() {

            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTable);
        return layout;
    }
   
    

    private VerticalLayout loadFreezeFilterTreeTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        FreezeFilterTreeTable freezeFilterTreeTable = new FreezeFilterTreeTable();
    TableExpandListener filtertableExpandListener = new TableExpandListener(freezeFilterTreeTable.getRightFreezeAsTable());
    TableCollapseListener filtertableCollapseListener = new TableCollapseListener(freezeFilterTreeTable.getRightFreezeAsTable());
    TableExpandListener filterfreezeTableExpandListener = new TableExpandListener(filtertableExpandListener);
    TableCollapseListener filterfreezeTableCollapseListener = new TableCollapseListener(filtertableCollapseListener);
        freezeFilterTreeTable.setCaption("FreezeFilterTreeTable");
        freezeFilterTreeTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setContainerDataSource(availableProductsBean);
        freezeFilterTreeTable.setPageLength(6);
        freezeFilterTreeTable.setSelectable(true);
        ExtFilterTreeTable leftTable = freezeFilterTreeTable.getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = freezeFilterTreeTable.getRightFreezeAsTable();
        
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        freezeFilterTreeTable.setDoubleHeaderVisible(true);
        freezeFilterTreeTable.setFilterBarVisible(true);

        freezeFilterTreeTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless,Utils.dviscolumnmore);
        freezeFilterTreeTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless,Utils.dvisheadermore);
        freezeFilterTreeTable.setDoubleHeaderMap(dMapVisibleColumnsLess,dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        leftTable.addExpandListener(filterfreezeTableExpandListener);
        leftTable.addCollapseListener(filterfreezeTableCollapseListener);
        rightTable.addExpandListener(filtertableExpandListener);
        rightTable.addCollapseListener(filtertableCollapseListener);
        layout.addComponent(freezeFilterTreeTable);
        return layout;
    }
    
private VerticalLayout loadPagedFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        ExtPagedFilterTable<IndexedContainer> pagedFilterTable = new ExtPagedFilterTable<IndexedContainer>();
        pagedFilterTable.setCaption("ExtPagedFilterTable");
        pagedFilterTable.setWidth("100%");

        pagedFilterTable.setFilterBarVisible(true);

        pagedFilterTable.setSelectable(true);
        pagedFilterTable.setImmediate(true);
        pagedFilterTable.setMultiSelect(true);

        pagedFilterTable.setRowHeaderMode(RowHeaderMode.INDEX);

        pagedFilterTable.setColumnReorderingAllowed(true);

        pagedFilterTable.setContainerDataSource(phasedProjectionBean);

        pagedFilterTable.setVisibleColumns(Utils.viscolumnmore);
        pagedFilterTable.setColumnHeaders(Utils.visheadermore);
        pagedFilterTable.setDoubleHeaderVisible(true);

        pagedFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        pagedFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        pagedFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        pagedFilterTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {

            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }
        });
        pagedFilterTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {

            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }

        });
        layout.addComponent(pagedFilterTable);
        layout.addComponent(pagedFilterTable.createControls());
        return layout;
    }
    
    
    private VerticalLayout loadFreezePagedFilterTable() {
        VerticalLayout layout=new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        FreezePagedFilterTable<IndexedContainer> pagedFreezeFilterTable = new FreezePagedFilterTable<IndexedContainer>();

        pagedFreezeFilterTable.setCaption("FreezePagedFilterTable");
        pagedFreezeFilterTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setContainerDataSource(phasedProjectionBean);
        pagedFreezeFilterTable.setPageLength(6);
        pagedFreezeFilterTable.setSelectable(true);
        
        ExtPagedFilterTable leftTable = pagedFreezeFilterTable.getLeftFreezeAsTable();
        final ExtPagedFilterTable rightTable = pagedFreezeFilterTable.getRightFreezeAsTable();
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        pagedFreezeFilterTable.setDoubleHeaderVisible(true);
        pagedFreezeFilterTable.setFilterBarVisible(true);
        pagedFreezeFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless,Utils.dviscolumnmore);
        pagedFreezeFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless,Utils.dvisheadermore);
        pagedFreezeFilterTable.setDoubleHeaderMap(dMapVisibleColumnsLess, dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(pagedFreezeFilterTable);
        layout.addComponent(pagedFreezeFilterTable.createControls());
        return layout;
    }
    
   @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
