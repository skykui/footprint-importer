package sg.asia21at.webdev.footprintimporter.util;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TableUtil {
	private static boolean DEBUG = false;
	/*
	 * This method picks good column sizes. If all column heads are wider than
	 * the column's cells' contents, then you can just use
	 * column.sizeWidthToFit().
	 */
	private TableUtil(){
		
	}
	public static void initColumnSizes(JTable table, int[] minWidth) {
		
		TableModel model =  table.getModel();
		int Col = model.getColumnCount();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
//		Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < Col; i++) {
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;
//			System.out.println(headerWidth);

//			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i],
//					false, false, 0, i);
//			cellWidth = comp.getPreferredSize().width;

			if (DEBUG) {
				System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
						+ "; minWidth = " + minWidth[i]);
			}

			column.setPreferredWidth(Math.max(headerWidth, minWidth[i]));
		}
	}
}
