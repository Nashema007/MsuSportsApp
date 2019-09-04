package com.example.msusportsapp.utilities;

import android.view.animation.DecelerateInterpolator;

import com.scichart.charting.model.dataSeries.IXyDataSeries;
import com.scichart.charting.modifiers.ModifierGroup;
import com.scichart.charting.modifiers.PieSegmentSelectionModifier;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.visuals.SciPieChartSurface;
import com.scichart.charting.visuals.axes.IAxis;
import com.scichart.charting.visuals.legend.SciChartLegend;
import com.scichart.charting.visuals.renderableSeries.FastColumnRenderableSeries;
import com.scichart.charting.visuals.renderableSeries.IPieRenderableSeries;
import com.scichart.core.framework.UpdateSuspender;
import com.scichart.drawing.utility.ColorUtil;
import com.scichart.extensions.builders.SciChartBuilder;



import java.util.ArrayList;
import java.util.Collections;

public class DrawGraph {


    public static void columnGraph(final SciChartSurface surface, SciChartBuilder sciChartBuilder,
                                   String xAxisTitle, String yAxisTitle, ArrayList<Integer> xValues,ArrayList<Integer> yValues ){


        final IAxis xAxisColumn = sciChartBuilder
                .newNumericAxis()
                .withGrowBy(0, 0.9)
                .withAxisTitle(xAxisTitle)
                .build();
        final IAxis yAxisColumn = sciChartBuilder
                .newNumericAxis()
                .withGrowBy(0, 0.5)
                .withAxisTitle(yAxisTitle)
                .build();


        IXyDataSeries<Integer, Integer> dataSeries = sciChartBuilder.newXyDataSeries(Integer.class, Integer.class).build();
        dataSeries.setAcceptsUnsortedData(true);

        for (int i = 0; i < yValues.size(); i++) {
            dataSeries.append(xValues.get(i), yValues.get(i));
        }
        final FastColumnRenderableSeries rSeries = sciChartBuilder.newColumnSeries()
                .withStrokeStyle(0xFF232323, 0.8f)
                .withDataPointWidth(0.4)
                .withLinearGradientColors(ColorUtil.LightSteelBlue, ColorUtil.SteelBlue)
                .withDataSeries(dataSeries)
                .withPaletteProvider(new ColumnsPaletteProvider())
                .build();


        ModifierGroup chartModifierGroup = sciChartBuilder.newModifierGroup()
                .withMotionEventsGroup("SharedMotionEvents").withReceiveHandledEvents(true)
                .withCursorModifier().withShowTooltip(true).build()
                .build();


        UpdateSuspender.using(surface, () -> {
            Collections.addAll(surface.getXAxes(), xAxisColumn);
            Collections.addAll(surface.getYAxes(), yAxisColumn);
            Collections.addAll(surface.getRenderableSeries(), rSeries);
            Collections.addAll(surface.getChartModifiers(),chartModifierGroup );

            sciChartBuilder.newAnimator(rSeries).withWaveTransformation().withInterpolator(new DecelerateInterpolator()).withDuration(3000).withStartDelay(350).start();
        });




    }

    public static void pieChart(final SciPieChartSurface pieChartSurface, SciChartBuilder sciChartBuilder, SciChartLegend legend, ArrayList<Integer> pieValues) {


        final IPieRenderableSeries pieSeries = sciChartBuilder.newPieSeries().withSegments(
                sciChartBuilder.newPieSegment().withValue(pieValues.get(0)).withTitle("Level 1")
                        .withRadialGradientColors(0xff84BC3D, 0xff5B8829)
                        .build(),
                sciChartBuilder.newPieSegment().withValue(pieValues.get(1)).withTitle("Level 2").withRadialGradientColors(0xffe04a2f, 0xffB7161B).build(),
                sciChartBuilder.newPieSegment().withValue(pieValues.get(2)).withTitle("Level 3").withRadialGradientColors(0xff4AB6C1, 0xff2182AD).build(),
                sciChartBuilder.newPieSegment().withValue(pieValues.get(3)).withTitle("Level 4").withRadialGradientColors(0xffFFFF00, 0xfffed325).build(),
                sciChartBuilder.newPieSegment().withValue(pieValues.get(4)).withTitle("Level 5").withRadialGradientColors(0xffFFFF00, 0xfffed325).build()
        ).build();

        Collections.addAll(pieChartSurface.getRenderableSeries(),pieSeries);
        Collections.addAll(pieChartSurface.getChartModifiers(), sciChartBuilder.newLegendModifier(legend).withSourceSeries(pieSeries).build(), new PieSegmentSelectionModifier());

        pieSeries.animate(800);
    }

}
